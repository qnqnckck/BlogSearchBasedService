package com.kelvin.bsbs.common.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class SuccessResponse<T>(
    val body: T,
    val meta: MetaResponse = MetaResponse()
)

inline fun <reified T : Any> T.toSuccessResponse(
    meta: MetaResponse = MetaResponse()
): SuccessResponse<T> {
    return SuccessResponse(this, meta)
}