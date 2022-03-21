package com.ihr360.res.configuration

import com.fasterxml.jackson.databind.module.SimpleModule
import com.ihr360.res.utils.JPAJacksonTupleSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.persistence.Tuple

/**
 * MVC Configuration for this spring-boot application
 */
@Configuration
class MvcConfigureAdapter: WebMvcConfigurer {
    /**
     * This definition add JPAJacksonTupleSerializer to the auto-configured Jackson2ObjectMapperBuilder
     * Check [https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#howto-customize-the-jackson-objectmapper]
     * Any beans of type `com.fasterxml.jackson.databind.Module` are automatically registered with the auto-configured `Jackson2ObjectMapperBuilder` and are applied to any ObjectMapper instances that it creates. This provides a global mechanism for contributing custom modules when you add new features to your application.
     */
    @Bean
    fun jacksonJPATupleSerializationModule() = SimpleModule().apply {
        addSerializer(Tuple::class.java, JPAJacksonTupleSerializer())
    }

    /**
     * Creates a bean named userArgumentResolver
     */
    @Bean
    fun userArgumentResolver() = UserArgumentResolver()

    /**
     * Creates a bean named account argument resolver
     */
    @Bean
    fun accountArgumentResolver() = AccountArgumentResolver()

    /**
     * Creates a bean that creates authorized user information
     */
    @Bean
    fun sessionUserSettingArgumentResolver() = SessionUserSettingArgumentResolver()

    /**
     * Registers all custom argument resolvers that can be injected into handler methods as additional parameter
     */
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        with(resolvers) {
            add(userArgumentResolver())
            add(accountArgumentResolver())
            add(sessionUserSettingArgumentResolver())
        }
    }
}