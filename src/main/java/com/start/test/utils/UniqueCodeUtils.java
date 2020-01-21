package com.start.test.utils;

import java.util.UUID;

/**
 * @author st  <hushunting@startdt.com>
 * @date 2018/11/28 2:26 PM
 */
public class UniqueCodeUtils {


    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }


}
