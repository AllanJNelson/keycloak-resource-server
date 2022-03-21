package com.ihr360.res.dto.res

class BaseResponse<T> {
    var code = 0
    var message: String? = null
    var url: String? = null
    var errorResult = false
    var showType = 0
    var data: T? = null
}