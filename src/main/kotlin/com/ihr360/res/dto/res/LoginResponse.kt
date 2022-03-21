package com.ihr360.res.dto.res

class LoginResponse {
    var oauthToken = ""
    var refreshToken = ""
    var expiresIn = 0L
    var scope = ""
    var privileges = listOf<String>()
}