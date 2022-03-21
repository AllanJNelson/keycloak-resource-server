package com.ihr360.res.dto.req

import java.util.*

class EditAgreementRequest {
    var agreementCompanyId = UUID(0, 0)
    var agreementCompanyName = ""
    var beginDate = ""
    var endDate = ""
    var number = ""
    var remark = ""
    var staffId = UUID(0, 0)
    var staffName = ""
    var type = ""
}