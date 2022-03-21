package com.ihr360.res.configuration

import com.ihr360.res.dto.SessionUserSetting
import com.ihr360.res.service.AuthTokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class SessionUserSettingArgumentResolver: HandlerMethodArgumentResolver {
    @Autowired
    private lateinit var authTokenService: AuthTokenService

    override fun supportsParameter(param: MethodParameter): Boolean {
        return param.parameterType == SessionUserSetting::class.java
    }

    override fun resolveArgument(p0: MethodParameter, p1: ModelAndViewContainer?, p2: NativeWebRequest, p3: WebDataBinderFactory?): Any? {
        return authTokenService.getAuthorizedUserInfo()
    }
}
