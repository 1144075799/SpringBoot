package com.spring.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis工具类
 */
@Component
public class RedisClient {

    @Autowired
    private StringRedisTemplate redisTemplate;  //JDBCTemplate

    /**
     * 设置key—value到redis中
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key,String value){
        try {
            redisTemplate.opsForValue().set(key,value);
//            redisTemplate.opsForValue().set("name","Tom");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 通过key获取缓存里面的值
     * @param key
     * @return
     */
    public String get(String key){
        return redisTemplate.opsForValue().get(key);
    }

}
