package com.kelvin.bsbs.endpoints.ranking.service

import com.kelvin.bsbs.endpoints.ranking.dto.RankingKeywordItem
import com.kelvin.bsbs.endpoints.ranking.repository.RankingRepository
import org.springframework.stereotype.Service

@Service
class RankingService(
    private val rankingRepository: RankingRepository,
) {
    fun getSearchKeywordRank(): List<RankingKeywordItem> = rankingRepository.getSearchKeywordRank()
}