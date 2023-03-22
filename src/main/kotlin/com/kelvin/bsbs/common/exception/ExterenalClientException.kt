package com.kelvin.bsbs.common.exception

import com.kelvin.bsbs.common.annotation.ResponseError
import com.kelvin.bsbs.common.dto.ResponseCode
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseError(ResponseCode.EXTERNAL_CLIENT_ERROR)
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
open class ExternalClientException(e: RuntimeException?) : ServiceException(e)