package com.kafka.test.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.concurrent.*;

/**
 * @description: 订阅消息处理
 * @author: zhanghuiyong
 * @create: 2022-03-14 16:05
 */
@Slf4j
public abstract class AbstractConsumerHandler {

    protected static final int MAX_THREAD_POOL_NUM = 32;

    @Value("${spring.kafka.listener.concurrency}")
    private int concurrency;

    private final ConcurrentHashMap<String, ThreadPoolExecutor> consumerExecutors = new ConcurrentHashMap<>(concurrency * MAX_THREAD_POOL_NUM);

    /**
     * 消息提交
     */
    public void submit(List<ConsumerRecord<String, String>> consumerRecordList) {
        CountDownLatch countDownLatch = new CountDownLatch(consumerRecordList.size());
        try {
            consumerRecordList.stream().forEach(consumerRecord -> {
                ThreadPoolExecutor executor = getConsumerExecutor(consumerRecord);

                executor.execute(() -> {
                    try {
                        long start = System.currentTimeMillis();
                        handle(consumerRecord);
                        log.info("[submit] handle cost:{}", System.currentTimeMillis() - start);
                    } catch (Throwable e) {
                        log.error("[submit] consumer executor ", e);
                    } finally {
                        countDownLatch.countDown();
                    }
                });

            });
            countDownLatch.await();
        } catch (InterruptedException e) {
            log.error("[submit] submit throw InterruptedException", e);
        }
    }

    /**
     * 执行业务逻辑
     *
     * @param consumerRecord
     */
    protected abstract void handle(ConsumerRecord<String, String> consumerRecord);

    /**
     * 获取线程池
     *
     * @param consumerRecord
     * @return
     */
    private ThreadPoolExecutor getConsumerExecutor(ConsumerRecord<String, String> consumerRecord) {
        String executorKey = getConsumerExecutorKey(consumerRecord);
        return consumerExecutors.computeIfAbsent(executorKey, k -> {
            ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 10, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(100),
                    r -> new Thread(r, getConsumerExecutorName() + "_thread_pool_" + executorKey),
                    new ThreadPoolExecutor.AbortPolicy());
            executor.allowCoreThreadTimeOut(true);
            return executor;
        });
    }

    /**
     * 线程池名称
     *
     * @return
     */
    protected abstract String getConsumerExecutorName();

    /**
     * 获取线程池key
     *
     * @param consumerRecord
     * @return
     */
    protected abstract String getConsumerExecutorKey(ConsumerRecord<String, String> consumerRecord);
}
