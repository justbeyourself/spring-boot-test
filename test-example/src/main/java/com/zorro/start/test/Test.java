package com.zorro.start.test;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2022-03-15 15:51
 */
public class Test {

    public static void main(String[] args) {
//        List<String> strings = new ArrayList<String>();
//
//        strings.add("a");
//
//        strings = strings.stream().filter(s->s.equals("2")).collect(Collectors.toList());
//
//        if(CollectionUtils.isEmpty(strings)){
//            System.out.println("empty");
//        }
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>(2);
        System.out.println(map.size());
    }
}
