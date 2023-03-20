package com.kelvin.bsbs.runtime.redis

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer


@Configuration
class RedisClientConfig {
    @Bean
    fun redisTemplate(connectionFactory: LettuceConnectionFactory): RedisTemplate<String, String> {
        val redisTemplate = RedisTemplate<String, String>()
        connectionFactory.let { redisTemplate.setConnectionFactory(it) }
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = GenericJackson2JsonRedisSerializer()
        return redisTemplate
    }
}