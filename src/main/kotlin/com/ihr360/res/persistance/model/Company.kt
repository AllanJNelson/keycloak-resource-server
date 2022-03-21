package com.ihr360.res.persistance.model

import java.util.*
import javax.persistence.*

/**
 * Enterprise - Under Account
 */
@Entity
class Company: BaseEntity<UUID>() {
    // Related account - the topmost entity
    @ManyToOne
    @JoinColumn(name="account_id", nullable = false)
    lateinit var account: Account

    // Related user - the user that manages this company
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    lateinit var user: User

    @OneToMany(mappedBy = "company")
    var departments: Set<Department> = hashSetOf()

    @Column(length=255)
    var name = ""


}
