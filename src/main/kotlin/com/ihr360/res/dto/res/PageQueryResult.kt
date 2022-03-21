package com.ihr360.res.dto.res

class PageQueryResult<T> {
    var page = 0
    var rows = 0
    var totalElements = 0
    var totalPages = 0

    var content: List<T> = listOf()
}