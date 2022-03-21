package com.ihr360.res.persistance.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

/**
 * Area entity
 */
@Entity
class Area {
    @Id
    var id: Int = 0

    @Column(unique = true, length = 10)
    var code = ""

    @Column(length=255)
    var name = ""

    @Column(length = 255)
    var englishName = ""


    @Column(length = 255)
    var japaneseName = ""

    @Column(length = 10)
    var parentCode = ""

    var level: Int = 0
}