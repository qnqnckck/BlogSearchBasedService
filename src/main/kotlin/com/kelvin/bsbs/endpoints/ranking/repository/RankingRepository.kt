package com.kelvin.bsbs.endpoints.ranking.repository

import com.kelvin.bsbs.endpoints.ranking.constant.RANKING_KEYWORD_REDIS_KEY
import com.kelvin.bsbs.endpoints.ranking.dto.RankingKeywordItem
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ZSetOperations.TypedTuple
import org.springframework.stereotype.Repository

@Repository
class RankingRepository(
    private val redisTemplate: RedisTemplate<String, String>,
){
    fun getSearchKeywordRank(): List<RankingKeywordItem> {
        val zSetOperations = redisTemplate.opsForZSet()
        val typedTuples = zSetOperations.reverseRangeWithScores(RANKING_KEYWORD_REDIS_KEY, 0, 9)  //score순으로 10개 보여줌
        return typedTuples!!.mapIndexed{ i: Int, stringTypedTuple: TypedTuple<String>? ->
            RankingKeywordItem(i+1, stringTypedTuple!!.value!!, stringTypedTuple.score!!.toInt())
        }.toList()
    }
}