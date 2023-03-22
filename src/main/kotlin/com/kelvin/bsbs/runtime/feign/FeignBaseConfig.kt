package com.kelvin.bsbs.runtime.feign

import com.kelvin.bsbs.common.extension.JsonHelper
import feign.Logger
import feign.Request
import feign.codec.Decoder
import feign.codec.Encoder
import feign.optionals.OptionalDecoder
import org.springframework.beans.factory.ObjectProvider
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.cloud.openfeign.FeignClientsConfiguration
import org.springframework.cloud.openfeign.support.AbstractFormWriter
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder
import org.springframework.cloud.openfeign.support.SpringDecoder
import org.springframework.cloud.openfeign.support.SpringEncoder
import org.springframework.context.annotation.Bean
import org.springframework.format.support.FormattingConversionService
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import java.util.concurrent.TimeUnit

class FeignBaseConfig : FeignClientsConfiguration() {

    private final val objectMapper =  JsonHelper.getObjectMapper()
    private final val messageConverters = HttpMessageConverters(MappingJackson2HttpMessageConverter(objectMapper))

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