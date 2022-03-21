package com.ihr360.res.dto.res.organization

import java.util.*

class CorporationContentDTO {
    var companyId = UUID(0, 0)
    var companyName = ""
    var createdDate: Date? = null
    var departmentIds: List<String> = listOf()
    var id = UUID(0, 0)
    var isApplyToAll = true
    var isDeleted = false
    var organizationChartId = UUID(0, 0)
    var parent = ""
    var staffIds: List<String> = listOf()
    var status = ""
    var userId = ""
}