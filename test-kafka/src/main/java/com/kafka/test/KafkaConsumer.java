package com.kafka.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.kafka.test.KafkaTopic.TEST_TOPIC;

/**
 * @description: kafka消费者
 * @author: zhanghuiyong
 * @create: 2022-03-09 18:01
 */
@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = TEST_TOPIC)
    public void listen(@Payload String message, Acknowledgment ack) {
        log.info("[KafkaConsumer]message:{}", message);
        ack.acknowledge();
    }
}

