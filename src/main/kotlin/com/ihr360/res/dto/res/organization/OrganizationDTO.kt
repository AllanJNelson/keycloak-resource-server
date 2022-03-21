package com.ihr360.res.dto.res.organization

import java.util.*

class OrganizationDTO {
    var capacity = 0
    var children: List<String> = listOf()
    var className = ""
    var departmentId = UUID(0, 0)
    var depth = 0
    var hasAuth = true
    var id = UUID(0, 0)
    var isVirtual = false
    var nodeId = ""
    var onJobHeadCount = 0
    var onJobTotalHeadCount = 0
    var parentId = ""
    var positionList: List<String> = listOf()
    var principalId = ""
    var principalName = ""
    var staffInfoList: List<String> = listOf()
    var text = ""
    var totalCapacity = 0
    var type = ""
}