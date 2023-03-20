package com.kelvin.bsbs.runtime.feign

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.kelvin.bsbs.common.extension.JsonHelper
import feign.Logger
import feign.Request
import feign.Response
import feign.codec.DecodeException
import feign.codec.Decoder
import feign.codec.Encoder
import feign.codec.ErrorDecoder
import feign.optionals.OptionalDecoder
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.ObjectProvider
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.cloud.openfeign.FeignClientsConfiguration
import org.springframework.cloud.openfeign.support.*
import org.springframework.context.annotation.Bean
import org.springframework.format.support.FormattingConversionService
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import java.util.concurrent.TimeUnit

class FeignBaseConfig : FeignClientsConfiguration() {

    protected final val objectMapper =  JsonHelper.getObjectMapper()
    private final val messageConverters = HttpMessageConverters(MappingJackson2HttpMessageConverter(objectMapper))

    protected var connectTimeout = 0
    protected var readTimeout = 0

    @Bean
    fun feignLoggerLevel(): Logger.Level {
        return Logger.Level.FULL
    }


    @Bean
    fun feignRequestOptions(): Request.Options {
        return Request.Options(1, TimeUnit.SECONDS, 3, TimeUnit.SECONDS, false)
    }


    override fun feignDecoder(customizers: ObjectProvider<HttpMessageConverterCustomizer>?): Decoder {
        return OptionalDecoder(ResponseEntityDecoder(SpringDecoder({ messageConverters }, customizers)))
    }

    override fun feignEncoder(
        formWriterProvider: ObjectProvider<AbstractFormWriter>?,
        customizers: ObjectProvider<HttpMessageConverterCustomizer>?
    ): Encoder {
        return SpringEncoder { messageConverters }
    }

    @Bean
    @ConditionalOnMissingBean
    override fun feignConversionService(): FormattingConversionService {
        return super.feignConversionService()
    }
}