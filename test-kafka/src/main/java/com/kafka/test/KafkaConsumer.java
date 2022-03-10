package com.kafka.test;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import static com.kafka.test.KafkaTopic.TEST_TOPIC;

/**
 * @description: kafka消费者
 * @author: zhanghuiyong
 * @create: 2022-03-09 18:01
 */
@Component
public class KafkaConsumer {

    @KafkaListener(topics = TEST_TOPIC)
    public void listen(ConsumerRecord message, Acknowledgment ack) {
        System.out.println(message);
        ack.acknowledge();
    }
}

