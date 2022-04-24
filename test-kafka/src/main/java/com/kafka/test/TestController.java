package com.kafka.test;

import com.alibaba.fastjson.JSONObject;
import com.kafka.test.domain.TestMessage;
import com.kafka.test.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/send")
    public void sendMessage1(@RequestParam("message") String message) {
        TestMessage testMessage = JSONObject.parseObject(message, TestMessage.class);
        kafkaProducer.sendMessage(KafkaTopic.TEST_TOPIC, testMessage.getOneid(), message);
    }
}
