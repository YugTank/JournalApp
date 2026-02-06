package com.example.journalApp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private final ObjectMapper mapper = new ObjectMapper();

    public <T> T get(String key,Class<T> entityClass){
        try {
            Object o = redisTemplate.opsForValue().get(key);
            if(o==null){ return null; }

            return mapper.readValue(o.toString(), entityClass);
            }
        catch (Exception e) {
            log.error("Redis get error",e);
            return null;
        }
    }

    public void set(String key,Object value,long ttl){
        try {
            String json = mapper.writeValueAsString(value);
            redisTemplate.opsForValue().set(key, json, ttl, TimeUnit.SECONDS);
        }
        catch (Exception e) {
            log.error("Redis set error: ",e);
        }
    }
}
