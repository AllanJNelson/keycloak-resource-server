package com.ihr360.res.service

import com.ihr360.res.persistance.dao.PrivilegeRepository
import com.ihr360.res.persistance.model.Privilege
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Service bean for Privilege Entity
 */
@Service
class PrivilegeService {
    @Autowired
    private lateinit var privilegeRepository: PrivilegeRepository

    fun createPrivilegeIfNotFound(name: String) = privilegeRepository.findByName(name) ?: Privilege(name)
}