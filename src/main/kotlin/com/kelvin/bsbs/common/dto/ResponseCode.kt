package com.kelvin.bsbs.common.dto

enum class ResponseCode(val code: Int, val message: String) {
    /**
     * COMMON
     */
    UNKNOWN_ERROR(-1, "알 수 없는 에러입니다."),
    SUCCESS(0, "성공"),
    INVALID_PARAMETER(2, "유효하지 않은 파라미터입니다."),
    EXTERNAL_CLIENT_ERROR(100, "외부 호출시 에러 발생")

}