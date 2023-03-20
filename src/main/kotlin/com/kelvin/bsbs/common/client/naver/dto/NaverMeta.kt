package com.kelvin.bsbs.common.client.naver.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class NaverMeta (
    @JsonProperty("total_count")
    val totalCount: Int,
    @JsonProperty("pageable_count")
    val pageableCount: Int,
    @JsonProperty("is_end")
    val isEnd: Int,
)