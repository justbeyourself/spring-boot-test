package com.kafka.test.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.List;
import java.util.concurrent.*;

/**
 * @description: 订阅消息处理
 * @author: zhanghuiyong
 * @create: 2022-03-14 16:05
 */
@Slf4j
public abstract class AbstractConsumerHandler {

    private CountDownLatch countDownLatch;

    protected static final int MAX_THREAD_POOL_NUM = 16;

    private static final ConcurrentHashMap<Integer, ThreadPoolExecutor> consumerExecutors = new ConcurrentHashMap<>(MAX_THREAD_POOL_NUM);

    /**
     * 消息提交
     */
    public void submit(List<ConsumerRecord<String, String>> consumerRecordList) {
        countDownLatch = new CountDownLatch(consumerRecordList.size());
        try {
          //todo 业务逻辑
            countDownLatch.await();
        } catch (InterruptedException e) {
            log.error("[KafkaConsumer] submit throw InterruptedException", e);
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
        Integer executorKey = getConsumerExecutorKey(consumerRecord);
        ThreadPoolExecutor executor = consumerExecutors.get(executorKey);
        if (null == executor) {
            executor = new ThreadPoolExecutor(1, 1, 10, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(100),
                    r -> new Thread(r, getConsumerExecutorName() + "_thread_pool_" + executorKey),
                    new ThreadPoolExecutor.AbortPolicy());
            consumerExecutors.put(executorKey, executor);
        }
        return executor;
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
    protected abstract Integer getConsumerExecutorKey(ConsumerRecord<String, String> consumerRecord);
}
