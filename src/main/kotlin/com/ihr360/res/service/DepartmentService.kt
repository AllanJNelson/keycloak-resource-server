package com.ihr360.res.service

import com.ihr360.res.persistance.dao.DepartmentRepository
import com.ihr360.res.persistance.model.Department
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Service bean for Department Entity
 */
@Service
class DepartmentService {
    @Autowired
    private lateinit var departmentRepository: DepartmentRepository

    fun save(department: Department) {
        departmentRepository.save(department)
    }
}