package com.start.test.aspect.aop;

import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hhh <hushunting@startdt.com>
 * @date 2019/10/12 2:13 下午
 **/
public class ThreadLocalAttrsUtils {

    private static ThreadLocal<Map<String, String>> threadLocal = new ThreadLocal<>();

    private static void set(String k, String v) {
        Map<String, String> attrs = threadLocal.get();
        if (CollectionUtils.isEmpty(attrs)) {
            Map<String, String> attrsMap = new HashMap<>();
            attrsMap.put(k, v);
            threadLocal.set(attrsMap);
            return;
        }
        attrs.put(k, v);
    }

    private static String get(String k) {
        Map<String, String> attrs = threadLocal.get();
        if (CollectionUtils.isEmpty(attrs)) {
            return "";
        }
        return attrs.get(k);
    }

    private static final String VERSION_KEY = "pf_version";

    public static void setPfVersion(String value) {
        set(VERSION_KEY, value);
    }

    public static PfVersionEnum getPfVersion() {
        String s = get(VERSION_KEY);
        System.out.println("threadLocal get success.");
        return PfVersionEnum.getPfVersionByCode(s);
    }

    public static void remove() {
        threadLocal.remove();
        System.out.println("threadLocal remove success.");
    }


}
