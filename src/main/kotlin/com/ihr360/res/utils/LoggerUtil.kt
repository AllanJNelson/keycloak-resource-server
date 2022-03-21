package com.ihr360.res.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Easy Logger Helper
 */
fun<T> logger(clazz:Class<T>): Logger = LoggerFactory.getLogger(clazz)