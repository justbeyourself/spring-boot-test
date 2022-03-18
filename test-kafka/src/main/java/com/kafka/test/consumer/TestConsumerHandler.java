package com.kafka.test.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.utils.Utils;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 测试类
 * @author: zhanghuiyong
 * @create: 2022-03-14 16:05
 */
@Component
@Slf4j
public class TestConsumerHandler extends AbstractConsumerHandler {

    private final ConcurrentMap<Integer, AtomicInteger> consumerCounterMap = new ConcurrentHashMap<>();

    private final ConcurrentMap<Integer, String> consumerExecutorKeyMap = new ConcurrentHashMap<>();

    @Override
    protected void handle(ConsumerRecord<String, String> consumerRecord) {
        try {
            log.info("[handle] consumerRecord:{}", consumerRecord.toString());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("[handle] throw InterruptedException ", e);
        }
    }

    @Override
    protected String getConsumerExecutorName() {
        return "test";
    }


    @Override
    protected String getConsumerExecutorKey(ConsumerRecord<String, String> consumerRecord) {
        int partition = consumerRecord.partition();
        int hashCode = Utils.toPositive(Utils.murmur2(consumerRecord.key().getBytes()));
        return consumerExecutorKeyMap.computeIfAbsent(hashCode, h ->
                partition + "_" + Utils.toPositive(nextValue(partition)) % MAX_THREAD_POOL_NUM);
    }

    private int nextValue(int partition) {
        AtomicInteger counter = consumerCounterMap.computeIfAbsent(partition, k -> new AtomicInteger(0));
        return counter.getAndIncrement();
    }
}
