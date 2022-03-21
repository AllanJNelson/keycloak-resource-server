package com.ihr360.res.beans

import com.ihr360.res.persistance.model.Account
import com.ihr360.res.persistance.model.Company
import com.ihr360.res.persistance.model.User
import com.ihr360.res.service.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

/**
 * The Bootstrap component that is triggered when Application is started.
 * Seed some initial data into the database on the application launch.
 */
@Component
class Bootstrap: ApplicationListener<ApplicationStartedEvent> {
    @Autowired
    private lateinit var roleService: RoleService

    @Autowired
    private lateinit var privilegeService: PrivilegeService

    @Autowired
    private lateinit var accountService: AccountService

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var companyService: CompanyService

    @Autowired
    private lateinit var areaService: AreaService

    /**
     * Triggered when this spring boot application is started
     * @param event - ApplicationStartedEvent parameter
     * 1. Seed Roles And Privileges
     * 2. Seed Initial Data (Example Account, Organization, Departments and a User)
     * 3. Seed area data if the area data does not exists
     */
    override fun onApplicationEvent(event: ApplicationStartedEvent) {
        seedRolesAndPrivileges()
        seedInit()
        seedArea()
    }

    /**
     * Seed Roles & Privileges needed for the application
     */
    private fun seedRolesAndPrivileges(){
        // Add some roles & privileges
    }


    /**
     * Seed initial data (Example accounts, organizations, departments, and user)
     */
    private fun seedInit(){
        val mobileNo = "13567190293"

        if (accountService.findByName(mobileNo) != null) {
            return
        }
        // Create a new accountZX
        val account = Account().apply{
            accountName = mobileNo
            accountType = "TRIAL"
            activated = true
            admin = true
            allowConcurrentLogin = false
            allowCreateCompany = true
            this.mobileNo = mobileNo
        }

        // Create a new user
        val user = User().apply {
            phoneNumber = mobileNo
            password = "12345"
        }
        account.user = user

        // Create a new organization
        val company = Company().apply {
            name = "杭州艾骊莎信息科技有限公司"
        }

        userService.save(user)
        accountService.save(account)

        company.account = account
        company.user = user
        companyService.save(company)
    }

    /**
     * Seed Area Data
     */
    private fun seedArea(){
        if (areaService.countRows() > 0){
            return
        }
        areaService.seedAreaData()
    }
}