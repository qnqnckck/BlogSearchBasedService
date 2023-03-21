package com.kelvin.bsbs.common.client.kakao

import com.kelvin.bsbs.common.client.naver.NaverClient
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Component

@Component
class KakaoFallbackFactory(
    private val naverClient: NaverClient
) : FallbackFactory<KakaoClient>{
    override fun create(cause: Throwable?): KakaoClient {
        return KakaoClientFallback(cause, naverClient)
    }
}