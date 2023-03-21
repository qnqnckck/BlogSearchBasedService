package com.kelvin.bsbs.endpoints.search.service

import com.kelvin.bsbs.common.client.kakao.dto.KakaoSearchBlogResponse
import com.kelvin.bsbs.endpoints.search.repository.SearchRepository
import org.springframework.stereotype.Service

@Service
class SearchService(
    private val searchRepository: SearchRepository
) {

    fun searchByQuery(query: String, content: String?, sort: String?, page: Int?, size: Int?): KakaoSearchBlogResponse = searchRepository.searchBlog(
        query = query,
        page = page,
        size = size,
        sort = sort,
    )
}