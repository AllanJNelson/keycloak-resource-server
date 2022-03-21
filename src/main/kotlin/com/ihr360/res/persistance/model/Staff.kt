package com.ihr360.res.persistance.model

import java.util.*
import javax.persistence.Entity

@Entity
class Staff: BaseEntity<UUID>(){
    var name = ""

    // The department that this staff belongs to
    var departmentId: UUID? = null
}