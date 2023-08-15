package com.test.redis.lettuce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @description: ${description}
 * @author: zorro
 * @create: 2022-04-26 10:51
 */
@Service
public class RedisService {

    private DefaultRedisScript<Object> unlockLua;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Boolean setBit(String key, long offset, boolean value) {
        return redisTemplate.opsForValue().setBit(key, offset, value);
    }

    public Boolean getBit(String key, long offset) {
        return redisTemplate.opsForValue().getBit(key, offset);
    }

    public Boolean setIfAbsent(String key, String value, long timeout, TimeUnit unit) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, timeout, unit);
    }

    /**
     * 执行lua脚本
     * 以解锁为例
     *
     * @param key
     * @param value
     */
    public void unlock(String key, String value) {
        redisTemplate.execute(unlockLuaScript(), Collections.singletonList(key), value);
    }

    @Bean
    public DefaultRedisScript<Object> unlockLuaScript() {
        DefaultRedisScript<Object> defaultRedisScript = new DefaultRedisScript<>();
        defaultRedisScript.setLocation(new ClassPathResource("lua/unlock.lua"));
        defaultRedisScript.setResultType(Object.class);
        return defaultRedisScript;
    }

}
