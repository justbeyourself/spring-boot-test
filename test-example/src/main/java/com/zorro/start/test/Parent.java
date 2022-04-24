package com.zorro.start.test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2022-04-06 20:07
 */
public abstract class Parent {
    private final ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();

    public abstract void exec();

    protected void put(String param) {
        concurrentHashMap.put(param, "ok");
    }

    protected String get(String param) {
        return concurrentHashMap.get(param);
    }

}
