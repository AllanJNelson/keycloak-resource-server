package com.ihr360.res.dto.res.roster

import java.util.*

class FindTransferPageDataContentDTO {
    var abandonReason = ""
    var companyId = UUID(0 ,0)
    var companySalaryProfileId = UUID(0, 0)
    var datasourceType = ""
    var departmentId = 0
    var departmentName = ""
    var detailEntityList: List<String> = listOf()
    var effectiveAt = ""
    var entryApplicationStatus = ""
    var fileIdList: List<String> = listOf()
    var id = UUID(0, 0)
    var imageIdList: List<String> = listOf()
    var positionName = ""
    var positiveStatus = ""
    var processId = ""
    var remark = ""
    var salary = {}
    var sendEmail = false
    var sendSms = false
    var staffId = UUID(0, 0)
    var staffName = ""
    var staffNo = ""
    var staffStatus = ""
    var transderApplyDate = ""
    var transferConfirmDate = ""
    var transferExpectDate = ""
    var transferMode = ""
    var transferTypeEntity: List<Objects> = listOf()
    var transferWarn = true
    var userId = UUID(0, 0)
}