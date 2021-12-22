package com.lxb.aftask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {
    @Autowired
    RedisTemplate redisTemplate;
    @Test
    void test(){
        redisTemplate.opsForList().
    }
}
