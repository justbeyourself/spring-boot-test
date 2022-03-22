package com.test.nacos.web;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2022-03-09 17:47
 */
@RestController
@RequestMapping("/nacos/test")
public class TestController {

    @NacosValue(value = "${test.value:123}", autoRefreshed = true)
    private String testValue;

    @Value(value = "${test.value1:123}")
    private String testValue1;

    @GetMapping("/getConfig")
    public String getConfig() {

        return testValue + "_" + testValue1;
    }
}
