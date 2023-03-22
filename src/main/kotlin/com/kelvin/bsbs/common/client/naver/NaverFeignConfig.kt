package com.kelvin.bsbs.common.client.naver

import com.kelvin.bsbs.runtime.feign.FeignBaseConfig
import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment

class NaverFeignConfig(val env: Environment) : FeignBaseConfig() {

    @Bean
    fun requestUserAgentInterceptor(): RequestInterceptor {
        return KaKaoClientInterceptor(
            env.getProperty("client.naver.client-id")!!,
            env.getProperty("client.naver.client-secret")!!,
        )
    }

    class KaKaoClientInterceptor(
        private val clientId: String,
        private val secret: String
    ) : RequestInterceptor {
        override fun apply(template: RequestTemplate) {
            template.header("X-Naver-Client-Id", clientId)
            template.header("X-Naver-Client-Secret", secret)
        }
    }
}
