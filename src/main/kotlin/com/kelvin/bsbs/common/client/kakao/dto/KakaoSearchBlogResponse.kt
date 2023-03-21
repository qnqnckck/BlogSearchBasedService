package com.kelvin.bsbs.common.client.kakao.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.kelvin.bsbs.common.client.naver.dto.NaverSearchBlogResponse
import com.kelvin.bsbs.common.extension.DEFAULT_TIME_FORMAT
import com.kelvin.bsbs.common.extension.toLocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
data class KakaoSearchBlogResponse(
    val meta: KakaoMeta?,
    val documents: List<KakaoBlogDocument>
){
    constructor(response: NaverSearchBlogResponse) : this(
        meta = KakaoMeta(
            totalCount = response.total,
            pageableCount = (response.total/response.display) +1,
            isEnd = ((response.display + response.start) >= response.total)
        ),
        documents = response.items.map{
            KakaoBlogDocument(
                title =  it.title,
                url = it.link,
                blogname = it.bloggername,
                thumbnail = "",
                datetime = it.postdate.toLocalDate().atStartOfDay().format(DEFAULT_TIME_FORMAT)
            )
        }.toList()
    )
}