package com.kelvin.bsbs.endpoints.search.repository

import com.kelvin.bsbs.common.client.kakao.KakaoClient
import org.springframework.stereotype.Repository

@Repository
class SearchRepository(
    private val kakaoClient: KakaoClient
) {
    fun searchBlog(query: String, sort: String, page: Int, size: Int) = kakaoClient.searchBlog(
        query = query,
        sort = sort,
        page = page,
        size = size
    )
}