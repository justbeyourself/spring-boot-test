package com.kafka.test.consumer;

import com.alibaba.fastjson.JSONObject;
import com.kafka.test.domain.ConsumerCache;
import com.kafka.test.domain.TestMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

/**
 * @description: 测试类
 * @author: zorro
 * @create: 2022-03-14 16:05
 */
@Component
@Slf4j
public class TestConsumerHandler extends AbstractConsumerHandler {

    private final ConsumerCache consumerCache = new ConsumerCache();

    @Override
    protected void handle(ConsumerRecord<String, String> consumerRecord) {
        try {
            TestMessage message = JSONObject.parseObject(consumerRecord.value(), TestMessage.class);

            log.info("[handle] consumerRecord:{}", message.toString());
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

        TestMessage message = JSONObject.parseObject(consumerRecord.value(), TestMessage.class);
        String key = message.getMrchCode() + message.getTenantId() + message.getOneid();
        return consumerCache.getConsumerExecutorKey(consumerRecord.partition(), consumerRecord.key(), MAX_THREAD_POOL_NUM);
    }
}
