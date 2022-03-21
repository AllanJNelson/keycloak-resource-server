package com.ihr360.res.configuration

import com.ihr360.res.persistance.model.User
import com.ihr360.res.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.MethodParameter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

/**
 * Argument Resolver for User
 * Purpose: Easily get user entity for currently logged in user
 * This resolver will resolve injected AppUser type parameter in controller methods
 */
class UserArgumentResolver : HandlerMethodArgumentResolver {
    @Autowired
    private lateinit var userService: UserService

    override fun supportsParameter(param: MethodParameter): Boolean {
        return param.parameterType == User::class.java
    }

    override fun resolveArgument(
            p0: MethodParameter,
            p1: ModelAndViewContainer?,
            p2: NativeWebRequest,
            p3: WebDataBinderFactory?): Any? {
        val principal = SecurityContextHolder.getContext().authentication.principal as? UserDetails
                ?: throw UnauthorizedUserException("Unauthorized User")
        return userService.findByPhoneNumber(principal.username)
                ?: throw UsernameNotFoundException("No user found with name  [${principal.username}]")
    }
}