package com.kelvin.bsbs.endpoints.ranking.controller

import com.kelvin.bsbs.common.dto.SuccessResponse
import com.kelvin.bsbs.common.dto.toSuccessResponse
import com.kelvin.bsbs.endpoints.ranking.dto.RankingKeywordItem
import com.kelvin.bsbs.endpoints.ranking.service.RankingService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "랭킹 API", description = "검색 기능을 제공합니다.")
@RestController
class RankingController(
    private val rankingService: RankingService,
) {

    /**
     * 검색어 랭킹 Top10 조회 API
     *
     * @return 검색어 랭킹 top 10
     */
    @GetMapping("/rank/v1")
    @Operation(
        summary = "검색어 실시간랭킹 Top10",
        description = "실시간으로 검색어로 사용된 top10의 랭킹을 제공한다.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "응답 성공"
            )
        ]
    )
    fun searchKeywordTop10(): SuccessResponse<List<RankingKeywordItem>> =
        rankingService.getSearchKeywordRank().toSuccessResponse()
}