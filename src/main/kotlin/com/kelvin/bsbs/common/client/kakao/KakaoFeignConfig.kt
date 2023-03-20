package com.kelvin.bsbs.common.client.kakao

import com.fasterxml.jackson.databind.ObjectMapper
import feign.Request
import feign.RequestInterceptor
import feign.RequestTemplate
import com.kelvin.bsbs.runtime.feign.FeignBaseConfig
import feign.Response
import feign.codec.ErrorDecoder
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment

class KakaoFeignConfig(val env: Environment) : FeignBaseConfig() {

    @Bean
    fun feignErrorDecoder(): ErrorDecoder {
        return kakaoErrorDecoder(super.objectMapper)
    }


    @Bean
    override fun feignRequestOptions(): Request.Options {
        this.connectTimeout = 10
        this.readTimeout = 10

        return super.feignRequestOptions()
    }

    @Bean
    fun requestUserAgentInterceptor(): RequestInterceptor {
        return KaKaoClientInterceptor((env.getProperty("client.kakao.api-key")!!))
    }

    class KaKaoClientInterceptor(private val apiKey: String) : RequestInterceptor {
        override fun apply(template: RequestTemplate) {
            template.header("Authorization", apiKey)
        }
    }

    class kakaoErrorDecoder(private val objectMapper: ObjectMapper) : ErrorDecoder {
        private val delegate = ErrorDecoder.Default()

        override fun decode(methodKey: String?, response: Response?): Exception? {
            if (response?.body() == null) return delegate.decode(methodKey, response)

//            val errorResponse = try {
//                objectMapper.readValue(response.body().asInputStream(), SaaErrorResponse::class.java)
//            } catch (e: Exception) {
//                return delegate.decode(methodKey, response)
//            }
//
//            return SaaFallbackException(
//                errorResponse.errorCode,
//                errorResponse.errorMsg
//            ).defaultServiceException
            return null
        }
    }
}
