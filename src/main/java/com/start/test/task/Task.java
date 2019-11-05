package com.start.test.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2019-09-26 19:32
 */
@Component
public class Task {

    @Scheduled(cron = "${task.time}")
    public void tast1() {
        System.out.println("task 1 runing.");
    }
}
