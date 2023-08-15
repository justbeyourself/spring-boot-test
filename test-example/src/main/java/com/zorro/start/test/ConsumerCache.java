package com.zorro.start.test;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @description: 缓存类
 * @author: zorro
 * @create: 2022-03-21 17:08
 */
public class ConsumerCache {

    private final Cache<Integer, String> consumerExecutorKeyMap;

    public ConsumerCache() {
        this.consumerExecutorKeyMap = CacheBuilder.newBuilder().expireAfterAccess(24 * 60 * 60 * 1000L, TimeUnit.MILLISECONDS).build();
    }

    private static String call() {
        return "call";
    }

    public void put(int partition, String key) {
        consumerExecutorKeyMap.put(partition, key);
    }

    public String get(int partition) throws ExecutionException {
        return consumerExecutorKeyMap.get(partition, ConsumerCache::call);
    }

    public static void main(String[] args) {
        ConsumerCache consumerCache = new ConsumerCache();
        consumerCache.put(1,"1111");

        ConsumerCache consumerCache1 = new ConsumerCache();

        try {
            System.out.println(consumerCache1.get(1));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
