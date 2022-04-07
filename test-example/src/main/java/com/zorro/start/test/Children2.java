package com.zorro.start.test;

import org.springframework.stereotype.Service;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2022-04-06 20:09
 */
@Service
public class Children2 extends Parent {
    @Override
    public void exec() {
        System.out.println(get("111"));
    }
}
