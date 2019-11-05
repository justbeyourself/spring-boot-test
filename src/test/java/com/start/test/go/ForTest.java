package com.start.test.go;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2019-08-30 16:45
 */
public class ForTest {

    private static Cache<String, Integer> localCache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS).build();

    public static void main(String[] args) {
       Integer i = 10;

        System.out.println(i==10);


    }

    public int findPairs(int[] nums, int k) {

        if (k >= 0) {
            Map<Integer, Integer> map = new HashMap<>();
            int m = 0;
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < nums.length; j++) {
                    if (i == j) {
                        continue;
                    }
                    int ii = nums[i];
                    int jj = nums[j];
                    if (ii >= jj) {
                        if (k == ii - jj) {
                            map.put(ii, jj);
                        }
                    } else {
                        if (k == jj - ii) {
                            map.put(jj, ii);
                        }
                    }
                }
            }
            return map.size();
        } else {
            return 0;
        }

    }


    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {

        int n = S.length();
        int[] match = new int[n];
        Arrays.fill(match, -1);

        for (int i = 0; i < indexes.length; i++) {

            int index = indexes[i];

            if (S.substring(index, index + sources[i].length()).equals(sources[i])) {
                match[index] = i;
            }
        }

        String ss = "";
        int i = 0;
        while (i < n) {
            if (match[i] >= 0) {
                ss = ss + targets[match[i]];
                i += sources[match[i]].length();
            } else {
                ss = ss + S.charAt(i++);
            }

        }
        return ss;
    }

    public int lengthOfLongestSubstring(String s) {

        int max = 0;
        int n = s.length();
        Map<Character, Integer> ss = new HashMap<>(s.length());
        for (int i = 0, j = 0; j < n; j++) {
            if (ss.containsKey(s.charAt(j))) {
                i = Math.max(ss.get(s.charAt(j)), i);
            }

            max = Math.max(max, j - i + 1);
            ss.put(s.charAt(j), j + 1);
        }
        return max;
    }
}
