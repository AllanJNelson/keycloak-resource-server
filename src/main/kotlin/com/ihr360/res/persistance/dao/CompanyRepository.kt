package com.ihr360.res.persistance.dao

import com.ihr360.res.persistance.model.Company
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

/**
 * Interface for CompanyDAO
 * Spring JPA will automatically generate a bean implementing this interface
 */
interface CompanyRepository: JpaRepository<Company, UUID>