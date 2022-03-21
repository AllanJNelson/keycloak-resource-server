package com.ihr360.res.controllers

import com.ihr360.res.consts.Roles
import com.ihr360.res.dto.AuthorityDTO
import com.ihr360.res.dto.SessionUserSetting
import com.ihr360.res.dto.res.AccountAttributeDTO
import com.ihr360.res.dto.res.BaseResponse
import com.ihr360.res.dto.res.MeResponse
import com.ihr360.res.persistance.model.User
import com.ihr360.res.service.CompanyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user/")
class UserController {
    @Autowired
    private lateinit var companyService: CompanyService

    /**
     *
     * Return my data from server.
     * /user/me
     */
    @GetMapping("me")
    fun me(user: User, userSettings: SessionUserSetting): ResponseEntity<BaseResponse<MeResponse>> {
        val account = user.account ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(
                BaseResponse<MeResponse>().apply {
                    data = MeResponse()
                    data?.apply {
                        accountId = account.id
                        accountName = account.accountName
                        mobileNo = account.mobileNo
                        userId = user.id
                        staffId = user.staffId
                        email = null
                        locked = false
                        activated = true
                        activationKey = null
                        resetPasswordKey = null
                        authorities = listOf(
                                AuthorityDTO(Roles.ROLE_HR_ADMIN),
                                AuthorityDTO(Roles.ROLE_STAFF),
                                AuthorityDTO(Roles.ROLE_USER))
                        brand = "prod"
                        target = null
                        targetRedirectUrl = null
                        currentCompany = companyService.toDTO(companyService.getCurrentCompany(userSettings))
                        boundCompanyList = user.companies.map {
                            companyService.toDTO(it)
                        }
                        accountType = "TRIAL"
                        locale = "zh_CN"

                        allowConcurrentLogin = false
                        allowCreateCompany = true
                        loginInRegisterPage = false
                        departmentIds = null
                        directDepartmentIds = null
                        checkBossAuthorizeList = null
                        activateAppUserCount = 3
                        admin = true
                        hr = true
                        bound = true
                        irenshiAdmin = false
                        staff = true
                        subHr = false
                        attributes = AccountAttributeDTO()
                    }
                })
    }
}