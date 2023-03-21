package com.kelvin.bsbs.common.client.naver

import com.kelvin.bsbs.common.client.naver.dto.NaverSearchBlogResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "naverClient",
    url = "\${client.naver.host}",
    configuration = [NaverFeignConfig::class],
    fallbackFactory = NaverFallbackFactory::class

)
interface NaverClient {

    @GetMapping("/v1/search/blog.json")
    fun searchBlog(
        @RequestParam query: String,
        @RequestParam display: Int?,
        @RequestParam start: Int?,
        @RequestParam sort: String?
    ): NaverSearchBlogResponse
}