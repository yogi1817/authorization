package com.spj.authorization.security.adapters;

import com.spj.authorization.security.ports.in.ICustomOAuthAdapter;
import com.spj.authorization.security.ports.out.IOAuthClient;
import com.spj.register.openapi.resources.AuthTokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @author Yogesh Sharma
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CustomOAuthAdapter implements ICustomOAuthAdapter {

    private final IOAuthClient ioAuthClient;

    @Override
    public AuthTokenResponse getOAuthToken(String authorization, String code) {
        return ioAuthClient.getOAuthToken(authorization, code);
    }
}
