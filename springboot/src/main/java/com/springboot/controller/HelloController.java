package com.springboot.controller;


import com.springboot.pojo.JsonData;
import com.springboot.pojo.User;
import com.springboot.task.AsyncTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

@RestController
public class HelloController {

    @Autowired
    private AsyncTask task;


    @RequestMapping("/")
    public String hello(){
        return "Hello SpringBoot 结构完整版";
    }

    @RequestMapping("/test")
    public Map<String,String> testMap(){
        Map<String,String> map=new HashMap<>();
        map.put("name","Tom");
        map.put("age","24");
        return map;
    }

    @GetMapping("/testjson")
    public Object testJson(){
        return new User(24,"123456",new Date());
    }



    @GetMapping("async_task")
    public JsonData exeTask() throws InterruptedException {

        long begin =System.currentTimeMillis();

//        task.task1();
//        task.task2();
//        task.task3();

        Future<String> task4=task.task4();
        Future<String> task5=task.task5();
        Future<String> task6=task.task6();

        for(;;){
            if (task4.isDone()&&task5.isDone()&&task6.isDone()){
                break;
            }

        }

        long end = System.currentTimeMillis();

        long total=end-begin;

        System.out.println("执行总耗时:"+total);

        return JsonData.buildSuccess(total);
    }






}
