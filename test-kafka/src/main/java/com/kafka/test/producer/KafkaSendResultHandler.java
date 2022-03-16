package com.kafka.test.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

/**
 * @description: 发送回调
 * @author: zhanghuiyong
 * @create: 2022-03-11 14:17
 */
@Component
@Slf4j
public class KafkaSendResultHandler implements ProducerListener {

    public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {
        log.info("Message send success.topic:{}\tpartition:{}\tkey:{}\tvalue:{}\toffset:{}",
                producerRecord.topic(),
                recordMetadata.partition(),
                producerRecord.key(),
                producerRecord.value(),
                recordMetadata.offset());
    }

    public void onError(ProducerRecord producerRecord, Exception exception) {
        log.info("Message send error : {}" + producerRecord.toString());
    }
}
