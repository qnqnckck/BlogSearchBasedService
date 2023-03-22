package com.kelvin.bsbs.common.extension

import com.kelvin.bsbs.common.constant.ISO_8061_TIMESTAMP
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun String.toLocalDate(): LocalDate {
    return try {
        LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyyMMdd"))
    } catch (e: DateTimeParseException) {
        throw IllegalArgumentException(e.message)
    }
}


val DEFAULT_TIME_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern(ISO_8061_TIMESTAMP)
