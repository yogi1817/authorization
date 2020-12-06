package com.spj.authorization.security.ports.in;

import com.spj.register.openapi.resources.AuthTokenResponse;

public interface ICustomOAuthAdapter {
    public AuthTokenResponse getOAuthToken(String authorization, String code);
}
