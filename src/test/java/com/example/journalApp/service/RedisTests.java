package com.example.journalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    @Disabled
    void anyTest(){
        //redisTemplate.opsForValue().set("email","abc@gmail.com");
        Object value = redisTemplate.opsForValue().get("email");
        System.out.println(value);
    }
}
