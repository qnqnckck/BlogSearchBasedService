package com.kelvin.bsbs.common.client.naver

import com.fasterxml.jackson.databind.ObjectMapper
import feign.Request
import feign.RequestInterceptor
import feign.RequestTemplate
import com.kelvin.bsbs.runtime.feign.FeignBaseConfig
import feign.Response
import feign.codec.ErrorDecoder
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.RequestHeader

class NaverFeignConfig(val env: Environment) : FeignBaseConfig() {

    @Bean
    override fun feignRequestOptions(): Request.Options {
        this.connectTimeout = 2
        this.readTimeout = 3

        return super.feignRequestOptions()
    }

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
