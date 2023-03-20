package com.kelvin.bsbs.runtime.feign

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients(
    basePackages = [
        "com.kelvin.bsbs.common.client"
    ]
)
class FeignConfig
