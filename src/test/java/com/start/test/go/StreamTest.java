package com.start.test.go;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2019-10-12 16:07
 */
public class StreamTest {
    public static void main(String[] args) {

        // distinct 去重
        List<String> list =
                Arrays.asList("a", "b", "c", "a").stream().distinct().collect(Collectors.toList());
        System.out.println(list);

        // filter 断言
        List<Integer> l = IntStream.range(1,10)
                .filter( i -> i % 2 == 0)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(l); //[2, 4, 6, 8]

        // map数据映射
        List<Integer> collect = Stream.of('a','b','c')
                .map( c -> c.hashCode())
                .collect(Collectors.toList());
        System.out.println(collect); //[97, 98, 99]
    }
}
