package com.ihr360.res.persistance.dao

import com.ihr360.res.persistance.model.Department
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

/**
 * Interface for DepartmentDAO
 * Spring JPA will automatically generate a bean implementing this interface
 */
interface DepartmentRepository: JpaRepository<Department, UUID>