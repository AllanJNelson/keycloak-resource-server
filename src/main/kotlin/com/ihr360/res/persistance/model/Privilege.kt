package com.ihr360.res.persistance.model

import javax.persistence.Entity

/**
 * Privilege - Defines User Privilege. Roles can have privileges
 */
@Entity
class Privilege(var name: String = "") : BaseEntity<Long>() {

}