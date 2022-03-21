package com.ihr360.res.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore
import javax.sql.DataSource

/**
 * The OAuth2ResourceServerConfiguration for the back-end api
 */
@Configuration
@EnableResourceServer
class OAuth2ResourceServerConfig: ResourceServerConfigurerAdapter() {

    @Autowired
    private lateinit var dataSource: DataSource

    /**
     * Define some rules for security
     */
    override fun configure(http: HttpSecurity) {
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // Only expose auth/login, auth/refresh urls unsecured
                .antMatchers("/auth/login", "/auth/refresh").permitAll()
                // Set all other resources protected
                .anyRequest().authenticated()
    }

    /**
     * Configure this resource-server
     */
    override fun configure(resources: ResourceServerSecurityConfigurer) {
        // Use tokenServices bean here
        resources.tokenServices(tokenServices())
    }


    /**
     * Configure the token services for this resource server
     */
    @Bean
    @Primary
    fun tokenServices(): DefaultTokenServices {
        val defaultTokenServices = DefaultTokenServices()
        defaultTokenServices.setTokenStore(tokenStore())
        return defaultTokenServices
    }

    /**
     * Token Store to store OAuth2 token of the api consumer
     */
    @Bean
    fun tokenStore() : TokenStore = JdbcTokenStore(dataSource)
}