package com.ihr360.res.service

import com.ihr360.res.persistance.dao.UserRepository
import com.ihr360.res.persistance.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder

import org.springframework.stereotype.Service
import java.security.cert.CertificateEncodingException

/**
 * Service bean for User Entity
 */
@Service
class UserService {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    /**
     * Save the user into user database
     */
    fun save(user: User) {
        val password = user.password
        /*
        Check if password is already b-crypt encoded
        If not b-crypt encoded, then encode the password
         */
        if (!password.startsWith("$2a$")) {
            user.password = passwordEncoder.encode(password)
        }
        userRepository.save(user)
    }

    fun delete(user: User) {
        userRepository.delete(user)
    }

    /**
     * Find User by Phone Number
     */
    fun findByPhoneNumber(phoneNumber: String): User?{
        return userRepository.findByPhoneNumber(phoneNumber)
    }
}