package com.ihr360.res.dto.res

import com.ihr360.res.dto.AuthorityDTO
import java.util.*

class CompanyDTO {
    var companyId = ""
    var companyName = ""
    var companyStatus = ""
    var hrContactName = ""
    var userId = ""
    var staffId = ""

    var baseUrl = ""
    var loginEntryUrl = ""
    var authorities = listOf<AuthorityDTO>()

    var status = ""
    var needInitializing = false
    var webUserConfigInitialized = true

    var brand = "prod"
    var target = 0
    var source = "ibdcpc"
    var keyword = ""
    var hrContactPosition = ""
    var versionCode = ""
    var companyAccountType = "CHARGE"

    var industry = ""
    var companyScale = ""

    var province = ""
    var registerMonth = ""
    var registerDate: Date? = null

    var companyCode = "LT-2020070902"
    var locked = false
    var deadLineTime: Date? = null
    var deadLineTimeLong: Long = 0
}