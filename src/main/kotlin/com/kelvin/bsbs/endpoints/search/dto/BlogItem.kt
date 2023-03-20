package com.kelvin.bsbs.endpoints.search.dto

import java.time.LocalDateTime

data class BlogItem (
    val title : String,
    val contents: String,
    val url: String,
    val blogname: String,
    val thumbnail: String,
    val datetime: LocalDateTime,
)