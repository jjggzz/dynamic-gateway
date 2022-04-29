package com.jgz.gateway.listener;

import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author jgz
 * @version 1.0.0
 * @date 2022/4/29 17:38
 * @description
 */
@Component
public class RefreshRouteListener implements MessageListener {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private ApplicationEventPublisher publisher;


    @Override
    public void onMessage(Message message, byte[] bytes) {
        String msg = (String) redisTemplate.getValueSerializer().deserialize(message.getBody());
        if ("refresh".equals(msg)) {
            publisher.publishEvent(new RefreshRoutesEvent(this));
        }
    }
}
