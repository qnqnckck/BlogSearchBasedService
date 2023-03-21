package com.kelvin.bsbs.runtime.advisor

import com.kelvin.bsbs.common.dto.ErrorResponse
import com.kelvin.bsbs.common.dto.MetaResponse
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
        logger.error("Exception message={}", e.message, e);
        return handleError(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler(
        IllegalArgumentException::class,
        MissingServletRequestParameterException::class
    )
    fun handleBadRequest(e: java.lang.Exception): ResponseEntity<ErrorResponse> {
        // 상세 서비스 에러 코드를 따로 정의하지 않았기 때문에, -1
        // 400X Client Bad Request로 Info 로 출력
        logger.info("Exception message={}", e.message, e)
        return handleError(HttpStatus.BAD_REQUEST, e)
    }

    fun handleError(responseStatus: HttpStatus?, e: Throwable): ResponseEntity<ErrorResponse> {
        return handleError(responseStatus, e, e.message)
    }

    fun handleError(
        responseStatus: HttpStatus?,
        e: Throwable?,
        message: String?
    ): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(responseStatus!!).body(ErrorResponse(meta = MetaResponse(-1)))
    }
}