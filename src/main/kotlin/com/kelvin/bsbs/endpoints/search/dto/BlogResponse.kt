package com.kelvin.bsbs.endpoints.search.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.kelvin.bsbs.common.client.kakao.dto.KakaoSearchBlogResponse
import com.kelvin.bsbs.common.client.naver.dto.NaverSearchBlogResponse
import com.kelvin.bsbs.common.extension.DEFAULT_TIME_FORMAT
import com.kelvin.bsbs.common.extension.toLocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
data class BlogResponse(
    val pagination: BlogPagination,
    val documents: List<BlogItem>
){
    constructor(): this(
        pagination = BlogPagination(
            totalCount = 0,
            pageableCount = 0,
            isEnd = true
        ),
        documents = emptyList()
    )

    constructor(response: NaverSearchBlogResponse) : this(
        pagination = BlogPagination(
            totalCount = response.total,
            pageableCount = response.total.div(response.display) + 1,
            isEnd = (response.display.plus(response.start) >= response.total)
        ),
        documents = response.items.map{
            BlogItem(
                title =  it.title,
                url = it.link,
                blogname = it.bloggername,
                thumbnail = "",
                datetime = it.postdate.toLocalDate().atStartOfDay().format(DEFAULT_TIME_FORMAT)
            )
        }.toList()
    )

    constructor(response: KakaoSearchBlogResponse) : this(
        pagination = BlogPagination(
            totalCount = response.meta!!.totalCount,
            pageableCount = response.meta!!.pageableCount,
            isEnd = response.meta!!.isEnd
        ),
        documents = response.documents.map{
            BlogItem(
                title =  it.title,
                url = it.url,
                blogname = it.blogname,
                thumbnail = it.thumbnail,
                datetime = it.datetime
            )
        }.toList()
    )
}