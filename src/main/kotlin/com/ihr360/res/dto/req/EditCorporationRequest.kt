package com.ihr360.res.dto.req

class EditCorporationRequest {
    var address = ""
    var areaCode = ""
    var bankAccount = ""
    var bankName = ""
    var cityCode = ""
    var companyName = ""
    var departmentIds: List<String> = listOf()
    var ids: List<String> = listOf()
    var insureDefinitionId = ""
    var isApplyToAll = true
    var phone = ""
    var provinceCode = ""
    var remarks = ""
    var staffIds: List<String> = listOf()
    var textArea = ""
    var textNo = ""
}