package com.start.test.go;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2019-02-25 11:13
 */
public class interrupteTest {

    public static void main(String[] args) {

        Integer i = new Integer(0);
        if(i.equals(0)) {
            System.out.println(test());
        }
    }

    private static String test() {

        try {

            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        Thread.currentThread().interrupt();


        return "ok";
    }
}
