package com.spj.authorization.security.client;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.spj.authorization.configs.ServiceConfig;
import com.spj.authorization.security.ports.out.IOAuthClient;
import com.spj.register.openapi.resources.AuthTokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.codec.binary.Base64;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Yogesh Sharma
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OAuthClient implements IOAuthClient {

    private final ServiceConfig serviceConfig;
    private final Gson gson;

    @Override
    public AuthTokenResponse getOAuthToken(String authorization, String code) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("grant_type", "authorization_code")
                .addFormDataPart("scope", "read")
                .addFormDataPart("code", code)
                .addFormDataPart("redirect_uri", "no-ops")
                .build();

        Request request = new Request.Builder()
                .url(serviceConfig.getOauthTokenUrl())
                .method("POST", body)
                .addHeader("Authorization", "Basic c3BqOnRoaXNpc3NlY3JldA==")
                .build();

        Map<String, String> token;
        Type empMapType = new TypeToken<Map<String, String>>() {
            private static final long serialVersionUID = 1L;
        }
                .getType();
        try {
            Response response = client.newCall(request).execute();
            BufferedReader in = new BufferedReader(new InputStreamReader(response.body().byteStream()));

            String responseBody = new BufferedReader(in)
                    .lines()
                    .collect(Collectors.joining("\n"));
            log.debug("responseBody -->{}", responseBody);

            token = gson.fromJson(responseBody, empMapType);
            return new AuthTokenResponse()
                    .accessToken(token.get("access_token")).
                    tokenType("bearer")
                    .refreshToken(token.get("refresh_token"))
                    .email(token.get("email"));
        } catch (Exception e) {
            log.error("Error calling auth service " + e.getMessage());
            throw new ServiceException("Failed to call oauth service");
        }
    }
}
