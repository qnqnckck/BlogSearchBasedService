package com.kelvin.bsbs.endpoints.search.repository

import com.kelvin.bsbs.common.client.kakao.KakaoClient
import com.kelvin.bsbs.common.client.naver.NaverClient
import com.kelvin.bsbs.common.extension.RedisHelper
import com.kelvin.bsbs.common.extension.getLogger
import com.kelvin.bsbs.endpoints.search.dto.BlogResponse
import org.springframework.stereotype.Repository

@Repository
class SearchRepository(
    private val naverClient: NaverClient,
    private val kakaoClient: KakaoClient,
    private val redisHelper: RedisHelper
) {

    companion object {
        private val logger = getLogger()
    }

    fun searchBlog(query: String, sort: String?, page: Int?, size: Int?): BlogResponse {
        redisHelper.increaseKeywordCount(query)

        return kotlin.runCatching {
            BlogResponse(
                kakaoClient.searchBlog(
                    query = query,
                    sort = sort,
                    page = page,
                    size = size
                )
            )
        }.getOrElse { it ->
            logger.error(it.toString())
            kotlin.runCatching {
                BlogResponse(
                    naverClient.searchBlog(
                        query = query,
                        sort = if (sort != null && sort == "recency") {
                            "date"
                        } else {
                            sort
                        },
                        start = (page ?: 1).let { (it - 1) * (size ?: 10) + 1 },
                        display = size
                    )
                )
            }.getOrElse {
                logger.error(it.toString())
                BlogResponse()
            }
        }
    }
}