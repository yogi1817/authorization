package com.spj.authorization.security.endpoints;

import com.spj.authorization.security.ports.in.ICustomOAuthAdapter;
import com.spj.register.openapi.endpoint.CustomOAuthApi;
import com.spj.register.openapi.resources.AuthTokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;


/**
 * @author Yogesh Sharma
 */
@Controller
@Slf4j
@RequiredArgsConstructor
public class CustomOAuthController implements CustomOAuthApi {

    private final ICustomOAuthAdapter iCustomOAuthAdapter;
    /*@RequestMapping(value = {"user"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> user(OAuth2Authentication user) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user", user.getUserAuthentication().getPrincipal());
        userInfo.put("authorities", AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));

        return userInfo;
    }*/

    @Override
    public ResponseEntity<AuthTokenResponse> getAccessToken(@NotNull @Valid String code, Optional<String> authorization) {
        return ResponseEntity.ok(iCustomOAuthAdapter.getOAuthToken(null, code));
    }

    /*@Override
    public ResponseEntity<AuthTokenResponse> getAccessToken(Optional<String> authorization, @NotNull @Valid String code) {
        return ResponseEntity.ok(iCustomOAuthAdapter.getOAuthToken(authorization, code));
    }*/

    @Override
    public ResponseEntity<AuthTokenResponse> getTest() {
        return ResponseEntity.ok(new AuthTokenResponse().email("hello"));
    }
}
