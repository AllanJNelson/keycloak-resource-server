package com.ihr360.res.persistance.model

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

/**
 * Department - Under company
 */
@Entity
class Department: BaseEntity<UUID>(){
    // Linked enterprise id
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    lateinit var company: Company

    // Level of the department
    var level = 0

    // Parent Department Id
    var parentId : UUID? = null

    // ID of root department
    var rootId: UUID? = null

    // Name of the department
    @Column(length = 255)
    var name = ""
}