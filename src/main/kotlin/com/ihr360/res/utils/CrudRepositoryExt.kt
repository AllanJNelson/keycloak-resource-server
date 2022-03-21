package com.ihr360.res.utils

import org.springframework.data.repository.CrudRepository

/**
 * extension to findById function of CrudRepository
 * returns kotlin optional type from Java Optional<T>
 */
fun <E, K> CrudRepository<E, K>.findByIdKt(id: K): E? {
    val o = findById(id)
    return if (o.isPresent) o.get() else null
}