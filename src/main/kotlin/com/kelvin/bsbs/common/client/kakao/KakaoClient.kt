package com.kelvin.bsbs.common.client.kakao

import com.kelvin.bsbs.common.client.kakao.dto.KakaoSearchBlogResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "kakaoClient",
    url = "\${client.kakao.host}",
    configuration = [KakaoFeignConfig::class],
    fallbackFactory = KakaoFallbackFactory::class
)
interface KakaoClient {

    @GetMapping("/v2/search/blog")
    fun searchBlog(
        @RequestParam query: String,
        @RequestParam(required = false) sort: String?,
        @RequestParam(required = false) page: Int?,
        @RequestParam(required = false) size: Int? = 10,
    ): KakaoSearchBlogResponse
}