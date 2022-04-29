package com.jgz.gateway.config;

import com.jgz.gateway.listener.RefreshRouteListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @author jgz
 * @version 1.0.0
 * @date 2022/4/29 17:37
 * @description
 */
@Configuration
public class RedisConfig {


    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory factory,
                                                   RefreshRouteListener refreshRouteListener
    ) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        //订阅频道redis.refreshRoute  这个container 可以添加多个 messageListener
        container.addMessageListener(refreshRouteListener, new ChannelTopic("redis.refreshRoute"));
        return container;
    }

}

