package com.kafka.test.domain;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.kafka.common.utils.Utils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 缓存类
 * @author: zhanghuiyong
 * @create: 2022-03-21 17:08
 */
public class ConsumerCache {

    private final ConcurrentMap<Integer, AtomicInteger> consumerCounterMap;

    private final Cache<Integer, String> consumerExecutorKeyMap;

    public ConsumerCache() {
        this.consumerCounterMap = new ConcurrentHashMap<>();
        this.consumerExecutorKeyMap = CacheBuilder.newBuilder().expireAfterAccess(24 * 60 * 60 * 1000L, TimeUnit.MILLISECONDS).build();
    }

    public String getConsumerExecutorKey(int partition, String key, int maxThreadPoolNum) {
        int hashCode = Utils.toPositive(Utils.murmur2(key.getBytes()));
        String executorKey = consumerExecutorKeyMap.getIfPresent(hashCode);
        if (executorKey == null) {
            executorKey = partition + "_" + nextValue(partition) % maxThreadPoolNum;
            consumerExecutorKeyMap.put(hashCode, executorKey);
        }
        return executorKey;
    }

    private int nextValue(int partition) {
        AtomicInteger counter = consumerCounterMap.computeIfAbsent(partition, k -> new AtomicInteger(0));
        return counter.getAndIncrement();
    }


}
