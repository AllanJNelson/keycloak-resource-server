package com.ihr360.res.persistance.dao

import com.ihr360.res.persistance.model.Area
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

/**
 * Interface for AreaDAO
 * Spring JPA will automatically generate a bean implementing this interface
 */
interface AreaRepository: JpaRepository<Area, UUID>{
}