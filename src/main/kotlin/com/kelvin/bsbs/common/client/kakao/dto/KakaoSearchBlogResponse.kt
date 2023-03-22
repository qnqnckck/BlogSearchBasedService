package com.kelvin.bsbs.common.client.kakao.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class KakaoSearchBlogResponse(
    val meta: KakaoMeta?,
    val documents: List<KakaoBlogDocument>
)