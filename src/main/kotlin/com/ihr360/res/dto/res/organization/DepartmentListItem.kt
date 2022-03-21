package com.ihr360.res.dto.res.organization

import java.util.*

class DepartmentListItem {
    var capacity = 0
    var children: List<Objects> = listOf()
    var className = ""
    var departmentId = ""
    var depth = 0
    var id = UUID(0, 0)
    var isVirtual = false
    var nodeId = ""
    var onJobHeadCount = 0
    var onJobTotalHeadCount = 0
    var positionList: List<String> = listOf()
    var principalId = ""
    var principalName = ""
    var staffInfoList: List<String> = listOf()
    var text = ""
    var totalCapacity = 0
    var type = ""
}