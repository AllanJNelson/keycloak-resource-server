package com.ihr360.res.controllers


import com.ihr360.res.dto.req.LoginRequest
import com.ihr360.res.dto.req.RefreshTokenRequest
import com.ihr360.res.dto.res.LoginResponse
import com.ihr360.res.service.AuthTokenService
import com.ihr360.res.service.UserService
import org.apache.commons.codec.binary.Base64
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.http.client.ClientHttpResponse
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.ResponseErrorHandler
import org.springframework.web.client.RestTemplate
import java.net.URI
import javax.validation.Valid

/**
 * Rest controller that handles authentication
 */
@RestController
@RequestMapping("/auth/")
class AuthController{

    @Autowired
    @Qualifier("CustomUserDetailsService")
    private lateinit var userDetailsService: UserDetailsService

    @Autowired
    private lateinit var userService: UserService

    @Value("\${oauth2.clientid}")
    private lateinit var clientId: String

    @Value("\${oauth2.clientsecret}")
    private lateinit var clientSecret: String

    @Value("\${oauth2.server}")
    private lateinit var oauth2Endpoint: String

    @Autowired
    private lateinit var authTokenService: AuthTokenService

    /**
     * Login Request Processing
     * Forwards the login request to local auth-server and sends back that response to the client
     */
    @PostMapping("login")
    fun login(@RequestBody @Valid r: LoginRequest): ResponseEntity<Any> {
        // Require Access Token from oauth2 server
        val entity = oauth2AccessToken(mapOf("grant_type" to "password", "username" to r.phoneNumber, "password" to r.password))
        if (entity.statusCode == HttpStatus.OK && entity.hasBody()) {
            val map = entity.body!!

            // Get privileges
            val authorities = try {
                userDetailsService.loadUserByUsername(r.phoneNumber) ?.authorities ?: listOf<GrantedAuthority>()
            }
            catch(ex: Exception) {
                listOf<GrantedAuthority>()
            }.map { it.authority!! }

            // Make the response
            val response = LoginResponse().apply {
                oauthToken = map["access_token"].toString()
                refreshToken = map["refresh_token"].toString()
                expiresIn = map["expires_in"].toString().toLongOrNull() ?: 0L
                scope = map["scope"].toString()
                privileges = authorities
            }

            // Find the access token in database and enhance it
            // So the corresponding information should be saved there.
            authTokenService.enhanceAccessToken(userService.findByPhoneNumber(r.phoneNumber)!!, response.oauthToken)

            return ResponseEntity.ok(response)
        }
        return ResponseEntity(entity.body, entity.statusCode)
    }

    /**
     * Refresh Request Processing
     * Forwards the token refresh request to local auth-server and sends back that response to the client
     */
    @PostMapping("refresh")
    fun refresh(@RequestBody @Valid r: RefreshTokenRequest) : ResponseEntity<Any>{
        val entity = oauth2AccessToken(mapOf("grant_type" to "refresh_token", "refresh_token" to r.refreshToken))
        if (entity.statusCode == HttpStatus.OK && entity.hasBody()) {
            val map = entity.body!!
            // Make the response
            val response = LoginResponse().apply {
                oauthToken = map["access_token"].toString()
                refreshToken = map["refresh_token"].toString()
                expiresIn = map["expires_in"].toString().toLongOrNull() ?: 0L
                scope = map["scope"].toString()
            }

            return ResponseEntity.ok(response)
        }
        return ResponseEntity(entity.body, entity.statusCode)
    }

    /**
     *  Connect to oauth2 server with data (username & password or refresh token)
     */
    private fun oauth2AccessToken(map:Map<String, String>): ResponseEntity<Map<String, Any>> {
        val restTemplate = RestTemplate()
        // Assign error handler
        restTemplate.errorHandler = object: ResponseErrorHandler {
            override fun hasError(resp: ClientHttpResponse): Boolean {
                return resp.statusCode.series() == HttpStatus.Series.CLIENT_ERROR || resp.statusCode.series() == HttpStatus.Series.SERVER_ERROR
            }
            override fun handleError(resp: ClientHttpResponse) {}
        }
        val credentials = "$clientId:$clientSecret"
        val encodedCredentials = Base64.encodeBase64String(credentials.toByteArray())

        val headers = HttpHeaders()
        headers.accept = mutableListOf(MediaType.APPLICATION_JSON)
        headers.contentType = MediaType.APPLICATION_FORM_URLENCODED
        headers.add("Authorization", "Basic $encodedCredentials")

        val url = "$oauth2Endpoint/oauth/token"
        val multivalueMap = LinkedMultiValueMap<String, String>(map.mapValues { entry ->  listOf(entry.value) })
        val requestEntity = RequestEntity(multivalueMap, headers, HttpMethod.POST, URI.create(url))

        return restTemplate.exchange(requestEntity, object: ParameterizedTypeReference<Map<String, Any>>() {})
    }
}