package com.zorro.start.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2022-03-29 20:51
 */
public class Test {


    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                r -> new Thread(r, "thread_pool)"),
                new ThreadPoolExecutor.AbortPolicy());

        executor.execute(() -> {
            Children1 children1 = new Children1();
            children1.exec();
        });

               executor.execute(() -> {
                   try {
                       Thread.sleep(1000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   Children1 children1 = new Children1();
                   children1.pull();
               });

    }


}
