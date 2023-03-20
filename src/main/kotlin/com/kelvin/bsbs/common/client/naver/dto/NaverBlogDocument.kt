package com.kelvin.bsbs.common.client.naver.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
class NaverBlogDocument(
    val title: String,
    val contents: String,
    val url: String,
    val blogname: String,
    val thumbnail: String,
    val datetime: LocalDateTime,
)