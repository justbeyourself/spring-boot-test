package com.zorro.start.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;

/**
 * @description: allowCoreThreadTimeOut 设置为true 可以回收核心线程
 * @author: zhanghuiyong
 * @create: 2022-03-15 15:51
 */
public class TestThreadPoolExecutor {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                r -> new Thread(r, "thread_pool)"),
                new AbortPolicy());
        executor.allowCoreThreadTimeOut(true);
        executor.execute(() -> {
            System.out.println("1111");
        });
        System.out.println(executor.getPoolSize() + "|" + executor.getActiveCount());

        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(executor.getPoolSize() + "|" + executor.getActiveCount());

        executor.execute(() -> {
            System.out.println("2222");
        });

        System.out.println(executor.getPoolSize() + "|" + executor.getActiveCount());
    }
}
