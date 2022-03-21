package com.ihr360.res.dto.req;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RefreshTokenRequest {
    @NotEmpty
    @NotNull
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
