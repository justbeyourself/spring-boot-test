package com.test.nacos.provider.impl;

import com.test.nacos.provider.TestProvider;
import com.test.nacos.provider.api.DubboResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @description: ${description}
 * @author: zorro
 * @create: 2022-03-24 19:08
 */
@Slf4j
@DubboService(version = "${test.dubbo.version}",
        executes = 200,
        timeout = 5000,
        retries = 3)
public class TestProviderImpl implements TestProvider {

    @Override
    public DubboResult<String> test1(String req) {
        log.info("dubbo test1 provider");
        return DubboResult.success(req);
    }
}
