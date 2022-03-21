package com.ihr360.res.service

import com.ihr360.res.persistance.dao.UserRepository
import com.ihr360.res.persistance.model.Role
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

/**
 * Service bean used for user-authentication
 * This class is for spring-security
 */
@Service
@Qualifier("CustomUserDetailsService")
class CustomUserDetailsService: UserDetailsService {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var accountService: AccountService

    /**
     * Finds the user with username(phonenumber here) and creates AppUser(UserDetails) and return it
     * Spring security will compare the password from request and do appropriate action
     */
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        // load by phone number
        val user = userRepository.findByPhoneNumber(username) ?: throw UsernameNotFoundException(username)

        // Assign additional properties
        return User(user.phoneNumber, user.password, user.isActive, !user.isExpired, true, true, getAuthorities(user.roles))
    }

    fun getAuthorities(roles:Set<Role>): Collection<GrantedAuthority>  {
        val authorities = mutableListOf<GrantedAuthority>()
        for (role in roles) {
            authorities.add(SimpleGrantedAuthority(role.role))
            authorities.addAll(role.privileges.map{ SimpleGrantedAuthority(it.name) })
        }
        return authorities
    }

    /**
     * We use BCryptPasswordEncoder for hashing user password
     */
    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}