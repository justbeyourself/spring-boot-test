package com.test.nacos;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySources;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description: ${description}
 * @author: zorro
 * @create: 2022-03-22 20:35
 */
@SpringBootApplication(scanBasePackages = {"com.test.nacos"})
@NacosPropertySources(value = {@NacosPropertySource(dataId = "nuza-common", autoRefreshed = true),
        @NacosPropertySource(dataId = "${spring.application.name}", autoRefreshed = true)
})
public class NacosApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosApplication.class, args);
    }
}
