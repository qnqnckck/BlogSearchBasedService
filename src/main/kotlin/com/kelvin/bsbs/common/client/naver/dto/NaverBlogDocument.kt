package com.kelvin.bsbs.common.client.naver.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
class NaverBlogDocument(
    val title: String,
    val description: String,
    val link: String,
    val bloggername: String,
    val bloggerlink: String,
    val postdate: String,
)