package com.kafka.test;

import com.kafka.KafkaTestApplication;
import com.kafka.test.producer.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description: ${description}
 * @author: zorro
 * @create: 2022-03-11 17:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KafkaTestApplication.class)
@Slf4j
public class KafkaTest {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Test
    public void producerTest() {
        String topic = KafkaTopic.TEST_TOPIC;
        String key = "100000143149640000009000000";
        for(int i = 0;i<10;i++){
            kafkaProducer.sendMessage(topic,key+i*1000000,"10000014308107"+i);
        }
    }
}
