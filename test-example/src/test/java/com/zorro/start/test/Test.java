package com.zorro.start.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2022-03-11 17:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
@Slf4j
public class Test {

    @Autowired
    private Children1 children1;

    @Autowired
    private Children2 children2;

    ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100),
            r -> new Thread(r, "thread_pool)"),
            new ThreadPoolExecutor.AbortPolicy());

    @org.junit.Test
    public void messageTest() {
        executor.execute(()->{
            log.info("1");
            children1.exec();
            log.info("11");
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.execute(()->{
            log.info("2");
            children2.exec();
            log.info("22");
        });


      //  children2.exec();
    }
}
