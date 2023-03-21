package com.kelvin.bsbs.common.client.naver.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class NaverSearchBlogResponse(
    val lastBuildDate: String,
    val total: Int,
    val start : Int,
    val display: Int,
    val items: List<NaverBlogDocument>
)