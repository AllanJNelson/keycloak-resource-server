package com.ihr360.res.persistance.model

import java.util.*
import javax.persistence.*

/**
 * The topmost entity for iHR360 system
 */
@Entity
class Account : BaseEntity<UUID>(){
    // The top
    // Phone Number
    @Column(unique = true, length = 255)
    var accountName = ""

    // Account Type
    var accountType = ""

    var activateAppUserCount = 0

    var activated = false

    var admin = false

    var allowConcurrentLogin = false

    var allowCreateCompany = true

    var mobileNo = ""

    var resetPasswordKey = ""

    var staff = false

    var subHr = false

    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    // Linked user id
    var user: User? = null


    @OneToMany(mappedBy = "account")
    var companies: Set<Company> = hashSetOf()
}