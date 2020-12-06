package com.spj.authorization.security.ports.out;

import com.spj.register.openapi.resources.AuthTokenResponse;


/**
 * @author Yogesh Sharma
 */
public interface IOAuthClient {
    public AuthTokenResponse getOAuthToken(String authorization, String code);
}
