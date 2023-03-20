package com.kelvin.bsbs.common.client.naver

import com.kelvin.bsbs.common.client.naver.dto.NaverSearchBlogResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

//@FeignClient(
//    name = "naverClient",
//    url = "\${client.naver.host}",
////    configuration = [AccountApiFeignConfiguration::class]
//)
//interface NaverClient {
//    @GetMapping("/v2/search/blog")
//    fun searchBlog(
//        @RequestHeader("X-Naver-Client-Id") clientId: String,
//        @RequestHeader("X-Naver-Client-Secret") secret: String,
//        @RequestParam query: String,
//        @RequestParam display: Int,
//        @RequestParam start: Int,
//        @RequestParam sort: String
//    ) : NaverSearchBlogResponse
//}