package com.ihr360.res.configuration

import com.ihr360.res.persistance.dao.AccountRepository
import com.ihr360.res.persistance.dao.UserRepository
import com.ihr360.res.persistance.model.Account
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.MethodParameter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

/**
 * Argument Resolver for Account
 * Get the account for logged in user
 */
class AccountArgumentResolver : HandlerMethodArgumentResolver {
    @Autowired
    private lateinit var accountRepository: AccountRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun supportsParameter(param: MethodParameter): Boolean {
        return param.parameterType == Account::class.java
    }

    override fun resolveArgument(
            p0: MethodParameter,
            p1: ModelAndViewContainer?,
            p2: NativeWebRequest,
            p3: WebDataBinderFactory?): Any? {
        val user = SecurityContextHolder.getContext().authentication.principal as? User ?: throw UnauthorizedUserException("Unauthorized")
        return userRepository.findByPhoneNumber(user.username)?.account
    }
}