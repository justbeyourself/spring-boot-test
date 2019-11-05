package com.start.test.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2019-08-08 19:52
 */
@Service()
@Qualifier("test2")
public class Test2Service {

    public String test2() {
        return "test2";
    }

    public String test3() {
        return "test3";
    }

    public String test4() {
        return "test4";
    }

    public String test5() {
        return "test5";
    }

    public String test6() {
        return "test6";
    }
}
