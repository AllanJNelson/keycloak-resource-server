package com.ihr360.res.dto

import java.util.*

class SessionUserSetting {
    /**
     * User ID
     */
    var userId = UUID(0, 0)

    /**
     * Associated account id (works for every kind of user)
     */
    var accountId = UUID(0, 0)

    /**
     * If the user is staff, then this should be the id of staff entity
     */
    var staffId: UUID? = null

    /**
     * Current Company Id (Currently selected enterprise ID)
     */
    var currentCompanyId : UUID? = null

    /**
     * Read from additional information of access token
     */
    fun readFromMap(map: Map<String, Any>) {
        userId = (map["userId"] as? UUID) ?: UUID(0, 0)
        accountId = (map["accountId"] as? UUID) ?: UUID(0, 0)
        staffId = map["staffId"] as? UUID
        currentCompanyId = map["currentCompanyId"] as? UUID
    }

    /**
     * Write the information to the map
     */
    fun write2Map(map: MutableMap<String, Any>) {
        map["accountId"] = accountId
        map["userId"] = userId
        if (staffId != null) {
            map["staffId"] = staffId!!
        }else {
            map.remove("staffId")
        }
        if (currentCompanyId != null) {
            map["currentCompanyId"] = currentCompanyId!!
        } else {
            map.remove("currentCompanyId")
        }
    }
}