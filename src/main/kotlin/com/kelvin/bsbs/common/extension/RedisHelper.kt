package com.kelvin.bsbs.common.extension

import com.kelvin.bsbs.endpoints.ranking.constant.RANKING_KEYWORD_REDIS_KEY
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component

@Component
class RedisHelper (
    private val redisTemplate: RedisTemplate<String, String>
){
    companion object{
        private val logger = getLogger()
    }
    fun increaseKeywordCount(query: String){
        try {
            redisTemplate.opsForZSet().incrementScore(RANKING_KEYWORD_REDIS_KEY, query, 1.0)
        } catch (e: Exception) {
            logger.error(e.toString())
        }
    }
}