package com.kafka.test.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.utils.Utils;
import org.springframework.stereotype.Component;

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
        return consumerRecord.partition()+"_"+Utils.toPositive(Utils.murmur2(consumerRecord.value().getBytes())) % MAX_THREAD_POOL_NUM;
    }
}
