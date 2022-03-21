package com.ihr360.res.persistance.dao

import com.ihr360.res.persistance.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

/**
 * Interface for AccountDAO
 * Spring JPA will automatically generate a bean implementing this interface
 */
interface AccountRepository: JpaRepository<Account, UUID> {
    /**
     * Find a account (the top-most level for this system) by account name
     */
    fun findByAccountName(accountName: String): Account?
}