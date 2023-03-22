package com.kelvin.bsbs.runtime.advisor

import com.kelvin.bsbs.common.dto.ErrorResponse
import com.kelvin.bsbs.common.dto.MetaResponse
import com.kelvin.bsbs.common.dto.ResponseCode
import com.kelvin.bsbs.common.exception.ServiceException
import com.kelvin.bsbs.common.extension.getLogger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalServiceExceptionHandler {

    companion object {
        private val logger = getLogger()
    }

    @ExceptionHandler(Exception::class)
    fun exceptionHandler(e: Exception): ResponseEntity<ErrorResponse> {
        logger.error("Exception message={}", e.message, e)
        return handleError(HttpStatus.INTERNAL_SERVER_ERROR, e)
    }

    @ExceptionHandler(ServiceException::class)
    fun serviceExceptionHandler(e: ServiceException): ResponseEntity<ErrorResponse> {
        logger.error("Exception message={}, code={}", e.message, e.getErrorCode())
        return handleError(e.getResponseStatus(), e, e.message, e.getErrorCode().code)
    }

    @ExceptionHandler(
        IllegalArgumentException::class,
        MissingServletRequestParameterException::class
    )
    fun handleBadRequest(e: java.lang.Exception): ResponseEntity<ErrorResponse> {
        logger.info("Exception message={}", e.message, e)
        return handleError(HttpStatus.BAD_REQUEST, e, e.message, ResponseCode.INVALID_PARAMETER.code)
    }

    fun handleError(responseStatus: HttpStatus?, e: Throwable): ResponseEntity<ErrorResponse> {
        return handleError(responseStatus, e, e.message, -1)
    }

    fun handleError(
        responseStatus: HttpStatus?,
        e: Throwable?,
        message: String?,
        code: Int,
    ): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(responseStatus!!).body(ErrorResponse(meta = MetaResponse(code)))
    }
}