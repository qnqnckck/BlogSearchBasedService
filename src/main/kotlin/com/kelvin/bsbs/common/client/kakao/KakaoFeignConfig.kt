package com.kelvin.bsbs.common.client.kakao

import com.kelvin.bsbs.runtime.feign.FeignBaseConfig
import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment

class KakaoFeignConfig(val env: Environment) : FeignBaseConfig() {

    @Bean
    fun requestUserAgentInterceptor(): RequestInterceptor {
        return KaKaoClientInterceptor((env.getProperty("client.kakao.api-key")!!))
    }

    class KaKaoClientInterceptor(private val apiKey: String) : RequestInterceptor {
        override fun apply(template: RequestTemplate) {
            template.header("Authorization", apiKey)
        }
    }
}
