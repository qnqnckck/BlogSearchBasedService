package com.kelvin.bsbs.common.client.naver

import com.kelvin.bsbs.common.exception.ExternalClientException
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Component

@Component
class NaverFallbackFactory: FallbackFactory<NaverClient>{
    override fun create(cause: Throwable?): NaverClient {
        throw ExternalClientException(IllegalStateException("naver External api Error"))
    }
}