package com.kafka.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @description: kafka 生产者
 * @author: zhanghuiyong
 * @create: 2022-03-09 17:57
 */
@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendMessage(String topic, String message) {
        System.out.println("[KafkaProducer]topic:" + topic + "\t message:" + message);
        kafkaTemplate.send(topic, message, message);
    }

}
