package com.zorro.start.test;

import org.springframework.stereotype.Service;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2022-04-06 20:09
 */
@Service
public class Children1 extends Parent {
    @Override
    public void exec() {
        put("111");
    }

    public void pull() {
        System.out.println(get("111"));
    }
}
