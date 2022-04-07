package com.test.redisson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2022-03-25 20:35
 */
@SpringBootApplication(scanBasePackages = {"com.test.redisson"})
public class RedissonApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedissonApplication.class, args);
    }
}
