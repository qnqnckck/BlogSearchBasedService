package com.kelvin.bsbs.common.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ErrorResponse(
    val meta: MetaResponse = MetaResponse()
)
