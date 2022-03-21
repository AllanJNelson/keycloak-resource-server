package com.ihr360.res.service

import com.ihr360.res.persistance.dao.RoleRepository
import com.ihr360.res.persistance.model.Role
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Service bean for Roles Entity
 */
@Service
class RoleService {
    @Autowired
    private lateinit var roleRepository: RoleRepository

    fun createRoleIfNotFound(role:String, description: String = ""): Role {
        val role = roleRepository.findByRole(role) ?: Role(role)
        role.roleDescription = description
        return role
    }
}