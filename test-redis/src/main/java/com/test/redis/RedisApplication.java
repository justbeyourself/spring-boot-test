package com.test.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description: ${description}
 * @author: zorro
 * @create: 2022-03-25 20:35
 */
@SpringBootApplication(scanBasePackages = {"com.test.redis"})
public class RedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }
}
