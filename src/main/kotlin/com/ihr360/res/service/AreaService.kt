package com.ihr360.res.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.ihr360.res.persistance.dao.AreaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

/**
 * Service bean for Area Entity
 */
@Service
class AreaService {
    @Autowired
    private lateinit var areaRepository: AreaRepository

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    fun countRows(): Long{
        return areaRepository.count()
    }

    fun seedAreaData(){
        val resource = ClassPathResource("AreaData.json")
        val mapper = ObjectMapper()

        val list:List<Map<String, Any>> = mapper.readValue(resource.inputStream, object: TypeReference<List<Map<String, Any>>>(){})
        for (jsonObj in list) {
            val sql = "INSERT INTO area(id, code, english_name, japanese_name, level, name, parent_code) VALUES('${jsonObj["id"]}', '${jsonObj["code"]}', '${jsonObj["english_name"]}', '${jsonObj["japanese_name"]}', '${jsonObj["level"]}', '${jsonObj["name"]}', '${jsonObj["parent_code"]}')"
            jdbcTemplate.execute(sql)
        }
    }

}