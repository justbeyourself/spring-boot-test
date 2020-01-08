package com.start.test.go;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.AbstractHash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.assertj.core.util.Lists;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2019-11-25 20:43
 */
public class Test {
    public static void main(String[] args) {
//        String hashAlgorithmName = Sha256Hash.ALGORITHM_NAME;
//        String credentials = "admin@123!";
//        int hashIterations = 1024;
//        SimpleHash obj = new SimpleHash(hashAlgorithmName, credentials, null, hashIterations);
//        System.out.println(obj.toString());
//        System.out.println(obj.toHex());

        List<String> userList = Lists.newArrayList();
        userList.add("1");
        userList.add("2");
        userList.add("3");
        System.out.println(userList.stream().filter(user1 -> "5".equals(user1)).findAny().get());

    }
}
