package com.test.redis.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheException;
import java.io.File;
import java.io.IOException;


/**
 * @description: ${description}
 * @author: zorro
 * @create: 2022-04-27 15:20
 */
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        Config config = null;
        try {
            config = Config.fromYAML(new File("classpath:redisson.yml"));
        } catch (IOException e) {
            throw new CacheException("Can't parse default yaml config", e);
        }
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }
}
