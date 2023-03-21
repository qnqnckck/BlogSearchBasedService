package com.kelvin.bsbs.endpoints.ranking.dto

import io.swagger.v3.oas.annotations.media.Schema

class RankingKeywordItem(
    @field:Schema(description = "순위", required = true, example = "1")
    val rank: Int,
    @field:Schema(description = "키워드", required = true, example = "테스트")
    val keyword: String,
    @field:Schema(description = "호출 횟수", required = true, example = "5")
    val count: Int
)