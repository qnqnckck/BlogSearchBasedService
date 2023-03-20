package com.kelvin.bsbs.common.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigInteger
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class MetaResponse(
    @field:Schema(description = "응답코드", required = true, example = "0(성공)")
    val responseCode: Int = ResponseCode.SUCCESS.code,
    @field:Schema(description = "요청 ID", required = true, example = "6fa7c129-97d6-42b0-8855-b79fdb4ec6ab")
    val requestId: String =  UUID.randomUUID().toString(),
    @field:Schema(description = "응답시간", required = true, example = "1672937847531")
    val timestamp: BigInteger = System.currentTimeMillis().toBigInteger(),
    @field:Schema(description = "응답 세부정보", required = false, example = "")
    val detail: String? = null
)