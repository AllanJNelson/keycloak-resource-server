package com.ihr360.res.service

import com.ihr360.res.dto.AuthorityDTO
import com.ihr360.res.dto.SessionUserSetting
import com.ihr360.res.dto.res.CompanyDTO
import com.ihr360.res.persistance.dao.CompanyRepository
import com.ihr360.res.persistance.dao.UserRepository
import com.ihr360.res.persistance.model.Company
import com.ihr360.res.persistance.model.User
import com.ihr360.res.utils.findByIdKt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

/**
 * Service bean for Company Entity
 */
@Service
class CompanyService {
    @Autowired
    private lateinit var companyRepository: CompanyRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var authTokenService: AuthTokenService


    fun save(company: Company) {
        companyRepository.save(company)
    }

    /**
     * Find company by id
     */
    fun find(id: UUID): Company? {
        return companyRepository.findByIdKt(id)
    }

    fun toDTO(company: Company): CompanyDTO {
        val dto = CompanyDTO()
        dto.companyId = company.id.toString()
        dto.companyName = company.name
        dto.companyStatus = "VALID"
        dto.hrContactName = "盛俊"
        dto.userId = company.user.id.toString()
        dto.staffId = "5a43bd72-6b47-4cd6-b56d-4ed401dc0dca"
        dto.baseUrl = "https://www.ihr360.com/gateway/"
        dto.loginEntryUrl = "https://www.ihr360.com/gateway/oauth2/authorize-client/ihr360"
        dto.authorities = listOf(AuthorityDTO("ROLE_HR_ADMIN"), AuthorityDTO("ROLE_STAFF"))
        dto.status = "NORMAL"
        dto.needInitializing = false
        dto.webUserConfigInitialized = false
        dto.brand = "prod"
        dto.target = 0
        dto.source = "ibdcpc"
        dto.keyword = "605irenshi"
        dto.hrContactPosition = "人事专员"
        dto.versionCode = "PROFESSIONAL"
        dto.companyAccountType = "CHARGE"
        dto.industry = "互联网/信息技术"
        dto.companyScale = "50人以下"
        dto.province = "浙江省"
        dto.registerMonth = "2020-06"
        dto.registerDate = Date()
        dto.companyCode = "LT-2020070902"
        dto.locked = false
        dto.deadLineTime = Date()
        dto.deadLineTimeLong = dto.deadLineTime!!.time
        return dto
    }

    /**
     * Get the currently selected company for this session
     */
    fun getCurrentCompany(setting:SessionUserSetting) : Company {
        val user = userRepository.findByIdKt(setting.userId)!!
        val companies = user.companies

        // Find company among the user's company
        val company = companies.find { it.id == setting.currentCompanyId }
        if (company != null){
            return company
        }

        // update the setting's current company id to the first company
        val firstCompany = companies.first()
        setting.currentCompanyId = firstCompany.id
        // Update user info
        authTokenService.updateUserInfo(setting)

        return firstCompany
    }
}