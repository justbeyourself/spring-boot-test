package com.test.nacos.provider;

import com.test.nacos.provider.api.DubboResult;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2022-03-24 19:26
 */
public interface TestProvider {

    /**
     * @return
     */
     DubboResult<String> test1(String req);
}
