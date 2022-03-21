package com.ihr360.res.service

import com.ihr360.res.dto.SessionUserSetting
import com.ihr360.res.persistance.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthTokenService {

    /**
     * OAuth2 Token Services
     */
    @Autowired
    private lateinit var tokenServices: DefaultTokenServices

    @Autowired
    private lateinit var tokenStore: TokenStore

    /**
     * Enhance access token when user is logged in
     * To make the auth server as simple as possible, hacking enhancing is done here
     */
    fun enhanceAccessToken(user: User, accessToken: String) {
        val authToken = tokenServices.readAccessToken(accessToken) ?: return
        val map = authToken.additionalInformation ?: return
        val info = SessionUserSetting().apply {
            userId = user.id
            //TODO: This logic should be re-implemented for other low-level staff users
            accountId = user.account?.id ?: UUID(0, 0)
            staffId = user.staffId
            // Set current company Id to null
            currentCompanyId = null
        }
        info.write2Map(map)

        val authentication = tokenStore.readAuthentication(authToken)
        tokenStore.storeAccessToken(authToken, authentication)
    }

    /**
     * Update the token database with the new information
     * Useful when setting current company id
     */
    fun updateUserInfo(info: SessionUserSetting) {
        val auth = SecurityContextHolder.getContext().authentication as? OAuth2Authentication ?: return
        val accessToken = tokenServices.getAccessToken(auth)

        // Re-write information to the additional information
        info.write2Map(accessToken.additionalInformation)

        // Update the token store
        tokenStore.storeAccessToken(accessToken, auth)
    }

    /**
     * Return stored authorized information from the token store
     */
    fun getAuthorizedUserInfo(): SessionUserSetting? {
        val auth = SecurityContextHolder.getContext().authentication as? OAuth2Authentication ?: return null
        val accessToken = tokenServices.getAccessToken(auth)
        val info = SessionUserSetting().apply {
            readFromMap(accessToken.additionalInformation)
        }
        return info
    }
}