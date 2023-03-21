package com.kelvin.bsbs.endpoints.search.controller

import com.kelvin.bsbs.endpoints.search.service.SearchService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "검색 API", description = "검색 기능을 제공합니다.")
@RestController
class SearchController(
    private val searchService: SearchService
){

    /**
     * 검색
     */
    @Operation(
        summary = "검색 질의  API",
        description = "테스트",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "응답 성공"
            )
        ]
    )
    @GetMapping("/search/v1")
    fun searchByQuery(
        @Parameter(required = true, description = "검색 원하는 질의어")
        @RequestParam(required = true)
        query: String,
        @Parameter(required = false, description = "검색 타겟",)
        @RequestParam(required = false, defaultValue = "BLOG")
        content: String?,
        @Parameter(required = false, description = "결과문서 정렬 방식 accuracy(정확도순) 또는 recency(최신순), 기본 값 accuracy",)
        @RequestParam(required = false)
        sort: String?,
        @Parameter(required = false, description = "결과 페이지 번호, 1~50 사이의 값, 기본 값 1",)
        @RequestParam(required = false)
        page: Int?,
        @RequestParam(required = false)
        size: Int?
    ) = searchService.searchByQuery(query, content, sort, page, size)


}