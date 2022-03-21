package com.ihr360.res.dto.res.roster

import java.util.*

class QuitPageDataContentDTO {
    var abandonReason = ""
    var applyDate = ""
    var blacklistStatus = ""
    var companyId = UUID(0, 0)
    var confirmQuitDate = ""
    var createdDate = ""
    var datasourceType = ""
    var departmentId = 0
    var departmentName = ""
    var enrollInDate = ""
    var expectQuitDate = ""
    var fileIdList: List<String> = listOf()
    var handoverStatus = ""
    var houseFundEndMonth = ""
    var id = UUID(0,0)
    var imageIdList: List<String> = listOf()
    var imgId = ""
    var lastUpdate = ""
    var lastWorkDay = ""
    var metaCode = ""
    var mobileNo = ""
    var positionId = UUID(0, 0)
    var positionName = ""
    var processId = ""
    var quitApplicationStatus = ""
    var quitFormAttachments = ""
    var quitReason = ""
    var quitReasonType: List<String> = listOf()
    var quitStatus = ""
    var quitType = ""
    var quitWarn = true
    var remark = ""
    var salarySettlement = ""
    var sendEmail = false
    var sendSms = false
    var socialInsureEndMonth = null
    var staffId = UUID(0, 0)
    var staffName = ""
    var staffNo = ""
    var systemTuningAppliaction = ""
    var systemTuningAppliactionName = ""
    var systemTuningDepartment = ""
    var systemTuningDepartmentName = ""
    var systemTuningSubordinate = ""
    var systemTuningSubordinateName = ""
    var userId = UUID(0, 0)
}