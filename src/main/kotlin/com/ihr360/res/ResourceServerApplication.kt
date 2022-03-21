package com.ihr360.res

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Main resource-server springboot application
 */
@SpringBootApplication
class ResourceServerApplication

fun main(args: Array<String>) {
	runApplication<ResourceServerApplication>(*args)
}
