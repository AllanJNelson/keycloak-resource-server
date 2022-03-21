package com.ihr360.res.service

import com.ihr360.res.persistance.dao.AccountRepository
import com.ihr360.res.persistance.model.Account
import com.ihr360.res.persistance.model.User
import com.ihr360.res.utils.findByIdKt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

/**
 * Service bean for Account Entity
 */
@Service
class AccountService {
    @Autowired
    private lateinit var accountRepository: AccountRepository

    fun findById(id: UUID) = accountRepository.findByIdKt(id)

    fun findByName(name: String) = accountRepository.findByAccountName(name)

    fun save(account: Account) = accountRepository.save(account)

    fun delete(account: Account) = accountRepository.delete(account)

    /**
     * Get linked account to the user
     */
    fun getLinkedAccount(user: User): Account? {
        return user.account
    }
}