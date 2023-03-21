package com.kelvin.bsbs.common.client.kakao.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
class KakaoBlogDocument(
    val title: String,
    val url: String,
    val blogname: String,
    val thumbnail: String,
    val datetime: String,
)