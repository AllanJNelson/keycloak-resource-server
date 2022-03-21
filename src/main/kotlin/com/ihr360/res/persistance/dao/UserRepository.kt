package com.ihr360.res.persistance.dao

import com.ihr360.res.persistance.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

/**
 * Interface for UserDAO
 * Spring JPA will automatically generate a bean implementing this interface
 */
@Repository
interface UserRepository: JpaRepository<User, UUID> {
    fun findByPhoneNumber(phoneNumber: String) : User?
}