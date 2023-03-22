package com.kelvin.bsbs.endpoints.ranking.repository

import com.kelvin.bsbs.endpoints.ranking.dto.RankingKeywordItem
import com.kelvin.bsbs.endpoints.search.repository.SearchRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("local")
internal class RankingRepositoryTest{
    @Autowired
    private lateinit var rankingRepository: RankingRepository


    @Autowired
    private lateinit var searchRepository: SearchRepository

    @Test
    @DisplayName("실시간 검색어 랭킹 업데이트 및 조회 - 10개 이하 ")
    fun rankingTest1() {
        //given
        val size = 10
        for (i in 1..4) {
            for(k in (5-i)..4) {
                searchRepository.searchBlog("keyword$i", null, null, null)
            }
        }

        //then
        val rankingKeywordItems: List<RankingKeywordItem> = rankingRepository.getSearchKeywordRank(size)
        var i = 4
        for (rankingKeywordItem in rankingKeywordItems) {
            Assertions.assertThat(rankingKeywordItem.keyword).isEqualTo("keyword$i")
            Assertions.assertThat(rankingKeywordItem.count).isEqualTo(i)
            i--
        }
    }
}