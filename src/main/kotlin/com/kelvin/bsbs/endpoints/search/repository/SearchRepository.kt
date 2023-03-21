package com.kelvin.bsbs.endpoints.search.repository

import com.kelvin.bsbs.common.client.kakao.KakaoClient
import com.kelvin.bsbs.common.client.kakao.dto.KakaoSearchBlogResponse
import com.kelvin.bsbs.common.extension.getLogger
import com.kelvin.bsbs.endpoints.ranking.constant.RANKING_KEYWORD_REDIS_KEY
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class SearchRepository(
    private val kakaoClient: KakaoClient,
    private val redisTemplate: RedisTemplate<String, String>,
) {

    companion object {
        private val logger = getLogger()
    }

    fun searchBlog(query: String, sort: String?, page: Int?, size: Int?): KakaoSearchBlogResponse {
        notifyBy(query)

        return kakaoClient.searchBlog(
            query = query,
            sort = sort,
            page = page,
            size = size
        )
    }

    private  fun notifyBy(query: String) {
        val score = 0.0
        try {
            redisTemplate.opsForZSet().incrementScore(RANKING_KEYWORD_REDIS_KEY, query, 1.0)
        } catch (e: Exception) {
            logger.error(e.toString())
        }

        redisTemplate.opsForZSet().incrementScore(RANKING_KEYWORD_REDIS_KEY, query, score)
    }
}