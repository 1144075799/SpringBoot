package com.springboot.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 定时任务物业类
 */
@Component
public class TestTask {

    @Scheduled(fixedRateString = "2000")                    //两秒执行一次
    //@Scheduled(cron = "*/2 * * * * *")
    public void sum() throws InterruptedException {

        Thread.sleep(4000L);
//        System.out.println("当前时间:"+new Date());
    }

}
