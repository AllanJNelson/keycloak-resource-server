package com.ihr360.res.dto.req

import java.util.*

//成本中心
class AddOrUpdateCostCenter {
    var costCode = ""
    var costName = ""
    var id = UUID(0, 0)
    var inputMode = true
    var isValid = true
}