package com.test.nacos.consumer;

import com.test.nacos.provider.TestProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2022-03-24 19:30
 */
@Slf4j
@Service
public class TestConsumerService {

    @DubboReference(version = "${test.dubbo.version}", check = false)
    private TestProvider testProvider;

    /**
     * @return
     */
    public String test() {
        log.info("consumer test test ");
        return testProvider.test1("798789897987").getData();
    }

}
