package com.spring.controller;

import com.spring.domain.JsonData;
import com.spring.domain.User;
import com.spring.utils.JsonUntils;
import com.spring.utils.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate redisTemplate;  //JDBCTemplate

    @Autowired
    private RedisClient redisClient;

    @GetMapping(value = "add")
    public Object add(){
        //opsForValue
//        redisTemplate.opsForValue().set("password","123456");

        redisClient.set("sex","boy");

        return JsonData.buildSuccess("OK");
    }

    @GetMapping(value = "get")
    public Object get(){
//        String value=redisTemplate.opsForValue().get("name");

        String value=redisClient.get("password");

        return JsonData.buildSuccess(value);
    }


    @GetMapping(value = "save_user")
    public Object saveUser(){
        User user=new User(18,"dasjklsaj","13868548597",new Date());
        String userStr= JsonUntils.obj2String(user);
        boolean value=redisClient.set("base:user:11",userStr);

        return JsonData.buildSuccess(value);
    }

    @GetMapping(value = "get_user")
    public Object getUser(){

        String userStr=redisClient.get("base:user:11");
        User user=JsonUntils.string2Obj(userStr,User.class);
        return JsonData.buildSuccess(user);
    }

}
