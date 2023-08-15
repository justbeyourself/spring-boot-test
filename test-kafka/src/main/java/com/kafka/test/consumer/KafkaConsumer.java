package com.kafka.test.consumer;

import com.alibaba.fastjson.JSON;
import com.kafka.test.domain.TestMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.kafka.test.KafkaTopic.TEST_TOPIC;

/**
 * @description: kafka消费者
 * @author: zorro
 * @create: 2022-03-09 18:01
 */
@Component
@Slf4j
public class KafkaConsumer {

    @Autowired
    private TestConsumerHandler testConsumerHandler;

    @KafkaListener(topics = TEST_TOPIC)
    public void listen(@Payload List<ConsumerRecord<String, String>> message, Acknowledgment ack) {
        long start = System.currentTimeMillis();
        log.info("[listen]message:{}", message.size());
        message.stream().forEach(record -> {
            log.info("KafkaConsumer:{}", JSON.toJSONString(record));
            TestMessage testMessage = JSON.parseObject(record.value(), TestMessage.class);
        });
//        testConsumerHandler.submit(message);
        ack.acknowledge();
        log.info("[listen] listen cost{}", System.currentTimeMillis() - start);


    }
}

