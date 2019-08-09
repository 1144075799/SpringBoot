package com.springboot.controller;


import com.springboot.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

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



}
