package com.kelvin.bsbs.common.client.naver

import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Component

@Component
class NaverFallbackFactory: FallbackFactory<NaverClient>{
    override fun create(cause: Throwable?): NaverClient {
        return NaverClientFallback(cause)
    }
}