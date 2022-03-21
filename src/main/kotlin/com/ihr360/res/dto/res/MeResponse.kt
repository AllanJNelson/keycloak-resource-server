package com.ihr360.res.dto.res

import com.ihr360.res.dto.AuthorityDTO
import java.util.*

class MeResponse {
    var accountId: UUID? = null
    var accountName = ""
    var accountNameWithAgent = ""

    var mobileNo = ""
    var username = ""
    var userId: UUID? = null
    var staffId: UUID? = null
    var email: String? = null

    var locked = false
    var activated = true
    var activationKey: String? = null
    var resetPasswordKey: String? = null

    var authorities = listOf<AuthorityDTO>()
    var brand = ""
    var target: String? = null
    var targetRedirectUrl: String? = null

    var currentCompany: CompanyDTO? = null
    var boundCompanyList: List<CompanyDTO> = listOf()

    var accountType = ""
    var locale = "zh_CN"

    var allowConcurrentLogin = false
    var allowCreateCompany = true
    var loginInRegisterPage = false
    var departmentIds: String? = null
    var directDepartmentIds: String? = null
    var checkBossAuthorizeList: List<Any>? = null
    var activateAppUserCount = 3
    var admin = true
    var hr = true
    var bound = true
    var irenshiAdmin = false
    var staff = true
    var subHr = false
    var attributes: AccountAttributeDTO? = null
}

class AccountAttributeDTO {
    var accountName = ""
    var accountNameWithAgent = ""
    var username = ""
    var activated = true
}