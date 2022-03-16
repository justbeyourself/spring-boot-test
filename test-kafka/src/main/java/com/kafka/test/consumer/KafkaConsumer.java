package com.kafka.test.consumer;

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
 * @author: zhanghuiyong
 * @create: 2022-03-09 18:01
 */
@Component
@Slf4j
public class KafkaConsumer {

    @Autowired
    private TestConsumerHandler testConsumerHandler;

    @KafkaListener(topics = TEST_TOPIC)
    public void listen(@Payload List<ConsumerRecord<String, String>> message, Acknowledgment ack) {
        log.info("[KafkaConsumer]message:{}", message.size());
        testConsumerHandler.submit(message);
        ack.acknowledge();
    }
}

