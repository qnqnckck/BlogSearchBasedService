package com.kelvin.bsbs.common.client.naver.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class NaverSearchBlogResponse(
    val meta: NaverMeta,
    val documents: List<NaverBlogDocument>
)