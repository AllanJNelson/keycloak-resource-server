package com.ihr360.res.dto.res.job

import java.util.*

class JobListItem {
    var companyId = UUID(0, 0)
    var description = ""
    var id = UUID(0, 0)
    var jobCategoryId = UUID(0, 0)
    var jobCategoryName = ""
    var jobTitleName = ""
    var maxPositionGradeId = UUID(0 , 0)
    var maxPositionGradeName = ""
    var minPositionGradeId = UUID(0, 0)
    var minPositionGradeName = ""
    var positionGradeList: List<String> = listOf()
}