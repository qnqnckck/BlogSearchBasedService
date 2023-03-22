package com.kelvin.bsbs.endpoints.search.dto

data class BlogPagination (
    val totalCount: Int,
    val pageableCount: Int,
    val isEnd: Boolean,
)