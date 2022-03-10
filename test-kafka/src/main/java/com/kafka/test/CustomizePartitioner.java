package com.kafka.test;

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
public class CustomizePartitioner implements Partitioner {

    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        //获取topic分区列表
        List<PartitionInfo> partitionerList = cluster.availablePartitionsForTopic(topic);
        return 1;
    }

    public void close() {
        System.out.println("close...");
    }

    public void configure(Map<String, ?> map) {
        System.out.println("init config...");

    }
}
