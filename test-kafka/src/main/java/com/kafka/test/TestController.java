package com.kafka.test;

import com.kafka.test.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2022-03-09 17:47
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @GetMapping("/send/{message}")
    public void sendMessage(@PathVariable("message") String message) {

        kafkaProducer.sendMessage(KafkaTopic.TEST_TOPIC, message);
    }
}
