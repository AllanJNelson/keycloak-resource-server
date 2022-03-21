package com.ihr360.res.persistance.dao

import com.ihr360.res.persistance.model.Role
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Interface for RoleDAO
 * Spring JPA will automatically generate a bean implementing this interface
 */
interface RoleRepository: JpaRepository<Role, Long> {
    /**
     * Find Roles by name
     * @param role : Roles to be searched
     * @return Roles object or null
     */
    fun findByRole(role: String): Role?
}