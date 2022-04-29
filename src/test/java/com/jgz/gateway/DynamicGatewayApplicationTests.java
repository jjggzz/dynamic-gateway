package com.jgz.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class DynamicGatewayApplicationTests {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        redisTemplate.convertAndSend("redis.refreshRoute","refresh");

    }

}
