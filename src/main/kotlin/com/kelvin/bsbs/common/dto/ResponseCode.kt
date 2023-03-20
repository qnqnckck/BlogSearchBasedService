package com.kelvin.bsbs.common.dto

enum class ResponseCode(val code: Int, val message: String) {
    /**
     * COMMON
     */
    UNKNOWN_ERROR(-1, "알 수 없는 에러입니다."),
    UNKNOWN_CODE(-2, "알 수 없는 코드입니다."),
    UNKNOWN_EXTERNAL_ERROR(-3, "알 수 없는 에러입니다."),
    SUCCESS(0, "성공"),

}