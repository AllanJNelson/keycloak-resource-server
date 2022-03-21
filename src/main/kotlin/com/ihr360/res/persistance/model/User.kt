package com.ihr360.res.persistance.model

import java.util.*
import javax.persistence.*

/**
 * The user entity
 */
@Entity
@Table(name= "users")
class User : BaseEntity<UUID>() {
    // Phone Number
    @Column(unique = true, length = 255)
    var phoneNumber = ""

    @Column(length = 255)
    var password= ""

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = [JoinColumn(name="user_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name="role_id", referencedColumnName = "id")])
    var roles:Set<Role> = hashSetOf()


    // Whether account is active or not
    var isActive = true

    // Whether account is expired or not
    var isExpired = false

    // Related top most account
    @OneToOne(mappedBy = "user")
    var account: Account? = null

    // In case of account user, related enterprises
    @OneToMany(mappedBy = "user")
    var companies: Set<Company> = hashSetOf()

    // This is not null when the user is staff
    var staffId:UUID? = null
}