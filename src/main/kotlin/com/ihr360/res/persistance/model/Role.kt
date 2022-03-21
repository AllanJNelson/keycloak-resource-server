package com.ihr360.res.persistance.model

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany

/**
 * Roles entity which consists of privileges
 */
@Entity
class Role(
        var role:String = "",
        var roleDescription:String = "") : BaseEntity<Long>() {

    @ManyToMany
    @JoinTable(
            name="role_privileges",
            joinColumns = [JoinColumn(
                    name = "role_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(
                    name = "privilege_id", referencedColumnName = "id")])
    var privileges:Set<Privilege> = hashSetOf()
}