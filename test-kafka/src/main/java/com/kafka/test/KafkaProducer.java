package com.kafka.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @description: kafka 生产者
 * @author: zhanghuiyong
 * @create: 2022-03-09 17:57
 */
@Component
@Slf4j
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaSendResultHandler kafkaSendResultHandler;

    public void sendMessage(String topic, String message) {
        log.info("[KafkaProducer]topic:{}\tmessage:{}", topic, message);
        kafkaTemplate.setProducerListener(kafkaSendResultHandler);
        kafkaTemplate.send(topic, message, message);
    }

    public void sendMessage(String topic, String key, String message) {
        log.info("[KafkaProducer]topic:{}\tkey:{}\tmessage:{}", topic, key, message);
        kafkaTemplate.setProducerListener(kafkaSendResultHandler);
        kafkaTemplate.send(topic, key, message);
    }
}
