package com.kelvin.bsbs.common.exception

import com.kelvin.bsbs.common.annotation.ResponseError
import com.kelvin.bsbs.common.dto.ResponseCode
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

open class ServiceException(e: Exception?) : RuntimeException(e) {
    fun getErrorCode(): ResponseCode {
        return this.javaClass.getAnnotation(ResponseError::class.java).errorCode
    }

    fun getResponseStatus(): HttpStatus {
        return this.javaClass.getAnnotation(ResponseStatus::class.java).value
    }
}