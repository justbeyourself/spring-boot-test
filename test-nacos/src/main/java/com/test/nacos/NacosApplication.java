package com.test.nacos;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2022-03-22 20:35
 */
@SpringBootApplication
@NacosPropertySource(dataId = "${spring.application.name}", autoRefreshed = true)
public class NacosApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosApplication.class, args);
    }
}
