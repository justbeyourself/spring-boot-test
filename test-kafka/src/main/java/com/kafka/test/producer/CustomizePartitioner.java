package com.kafka.test.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2022-03-10 13:56
 */
@Slf4j
public class CustomizePartitioner implements Partitioner {

    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        //获取topic分区列表
        List<PartitionInfo> partitionerList = cluster.availablePartitionsForTopic(topic);
        return 1;
    }

    public void close() {
        log.info("close...");
    }

    public void configure(Map<String, ?> map) {
        log.info("init config...");

    }
}
