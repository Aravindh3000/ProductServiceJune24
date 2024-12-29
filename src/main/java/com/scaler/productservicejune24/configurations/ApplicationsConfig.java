package com.scaler.productservicejune24.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

// to configure something that's not needed to be created more than once

@Configuration // one of special classes that contains bean annotation
public class ApplicationsConfig {
    @Bean // spring will allow to create only one object of this method
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // Handles connection
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
