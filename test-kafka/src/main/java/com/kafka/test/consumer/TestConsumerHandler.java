package com.kafka.test.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.utils.Utils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.*;

/**
 * @description: 测试类
 * @author: zhanghuiyong
 * @create: 2022-03-14 16:05
 */
@Component
@Slf4j
public class TestConsumerHandler extends AbstractConsumerHandler {

    @Override
    protected void handle(ConsumerRecord<String, String> consumerRecord) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("TestConsumerHandler throw InterruptedException ", e);
        }
    }

    @Override
    protected String getConsumerExecutorName() {
        return "test";
    }

    @Override
    protected Integer getConsumerExecutorKey(ConsumerRecord<String, String> consumerRecord) {
        return Utils.toPositive(Utils.murmur2(consumerRecord.toString().getBytes()));
    }
}
