package com.kelvin.bsbs.common.client.kakao

import com.kelvin.bsbs.common.client.kakao.dto.KakaoSearchBlogResponse
import com.kelvin.bsbs.common.client.naver.NaverClient

class KakaoClientFallback(val cause: Throwable?, private val naverClient: NaverClient) : KakaoClient {
    override fun searchBlog(query: String, sort: String?, page: Int?, size: Int?): KakaoSearchBlogResponse {
        var sort = sort
        if (sort != null && sort == "recency") {
            sort = "date"
        }

        var start = page?.let { it * (size ?: 0) + 1 } ?: 1

        return naverClient.searchBlog(
            query = query,
            sort = sort,
            start = start,
            display = size
        ).let {
            KakaoSearchBlogResponse(it)
        }
    }
}