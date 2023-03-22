package com.kelvin.bsbs.endpoints.search.repository

import com.kelvin.bsbs.common.client.kakao.KakaoClient
import com.kelvin.bsbs.common.client.naver.NaverClient
import com.kelvin.bsbs.common.client.naver.dto.NaverBlogDocument
import com.kelvin.bsbs.common.client.naver.dto.NaverSearchBlogResponse
import com.kelvin.bsbs.common.exception.ExternalClientException
import com.kelvin.bsbs.common.extension.RedisHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class SearchRepositoryTest {
    @InjectMocks
    private lateinit var searchRepository: SearchRepository

    @Mock
    private lateinit var naverClient: NaverClient

    @Mock
    private lateinit var kakaoClient: KakaoClient

    @Mock
    private lateinit var redisHelper: RedisHelper

    /**
     * Fallback 테스트 : 카카오실패 후 네이버 성공
     */
    @Test
    fun test_searchBlog_when_kakaoFallback_raised() {
        val query = "test"
        val sort = null
        val page = null
        val size = null
        val total = 100

        //GIVEN
        given(kakaoClient.searchBlog(query, sort, page, size)).willThrow(ExternalClientException::class.java)

        val naverParamStart = 1
        given(
            naverClient.searchBlog(
                query = query,
                sort = sort,
                start = naverParamStart,
                display = size
            )
        ).willReturn(getNaverSearchBlogResponse(total, naverParamStart, size ?: 10))

        // WHEN
        val result = searchRepository.searchBlog(query, sort, page, size)

        assertThat(result.pagination.totalCount).isEqualTo(total)
        assertThat(result.pagination.pageableCount).isEqualTo(total/(size?:10)+1)
        assertThat(result.pagination.isEnd).isFalse
        assertThat(result.documents.size).isEqualTo(10)
    }

    /**
     * Fallback 테스트 : 카카오 실패 및 네이버 실패 후 기본 값 리턴
     */
    @Test
    fun test_searchBlog_when_kakaoFallback_and_naverFallback_raised() {
        val query = "test"
        val sort = null
        val page = null
        val size = null

        //GIVEN

        given(kakaoClient.searchBlog(query, sort, page, size)).willThrow(ExternalClientException::class.java)

        val naverParamStart = 1
        given(
            naverClient.searchBlog(
                query = query,
                sort = sort,
                start = naverParamStart,
                display = size
            )
        ).willThrow(ExternalClientException::class.java)

        // WHEN
        val result = searchRepository.searchBlog(query, sort, page, size)

        assertThat(result.pagination.totalCount).isEqualTo(0)
        assertThat(result.pagination.pageableCount).isEqualTo(0)
        assertThat(result.pagination.isEnd).isTrue
        assertThat(result.documents.size).isEqualTo(0)

    }


    private fun getNaverSearchBlogResponse(total: Int, start: Int, display: Int): NaverSearchBlogResponse =
        NaverSearchBlogResponse(
            lastBuildDate = "Adf",
            total = total,
            start = start,
            display = display,
            // items =  for (i in  0 until display)
            items = (0 until display).map {
                NaverBlogDocument(
                    title = "title$it",
                    description = "description$it",
                    link = "link$it",
                    bloggername = "bloggername$it",
                    bloggerlink = "bloggerlink$it",
                    postdate = "20230321",
                )
            }.toList()
        )
}