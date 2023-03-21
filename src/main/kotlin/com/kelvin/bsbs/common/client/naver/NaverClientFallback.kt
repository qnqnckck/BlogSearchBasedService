package com.kelvin.bsbs.common.client.naver

import com.kelvin.bsbs.common.client.naver.dto.NaverSearchBlogResponse

class NaverClientFallback(val cause: Throwable?) : NaverClient {
    override fun searchBlog(query: String, display: Int?, start: Int?, sort: String?): NaverSearchBlogResponse {
        return NaverSearchBlogResponse(
            lastBuildDate= "",
            total = 0,
            start = 1,
            display = 10,
            items = emptyList()
        )
    }
}