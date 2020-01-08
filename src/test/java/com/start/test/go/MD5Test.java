package com.start.test.go;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.assertj.core.util.Lists;

import java.util.List;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2019-11-25 20:43
 */
public class MD5Test {
    public static void main(String[] args) {
        String hashAlgorithmName = Sha256Hash.ALGORITHM_NAME;
        String credentials = "admin@123!";
        int hashIterations = 1024;
        SimpleHash obj = new SimpleHash(hashAlgorithmName, credentials, null, hashIterations);
        System.out.println(obj.toString());
        System.out.println(obj.toHex());
    }
}
