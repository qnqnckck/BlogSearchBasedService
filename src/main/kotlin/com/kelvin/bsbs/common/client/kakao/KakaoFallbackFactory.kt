package com.kelvin.bsbs.common.client.kakao

import com.kelvin.bsbs.common.exception.ExternalClientException
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Component

@Component
class KakaoFallbackFactory : FallbackFactory<KakaoClient>{
    override fun create(cause: Throwable?) : KakaoClient {
        throw ExternalClientException(IllegalStateException("kakao External api Error"))
    }
}