package com.ihr360.res.persistance.dao


import com.ihr360.res.persistance.model.Privilege
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Interface for PrivilegeDAO
 * Spring JPA will automatically generate a bean implementing this interface
 */
interface PrivilegeRepository: JpaRepository<Privilege, Long> {
    /**
     * Find Privilege by name
     * @param name : Name to be searched
     * @return Privilege object or null
     */
    fun findByName(name:String): Privilege?
}