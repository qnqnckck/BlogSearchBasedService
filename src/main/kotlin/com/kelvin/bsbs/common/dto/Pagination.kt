package com.kelvin.bsbs.common.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Pagination<T>(
    val data: T,
    val totalCount: Int,
    val pageableCount: Int,
    val isEnd: Boolean
)