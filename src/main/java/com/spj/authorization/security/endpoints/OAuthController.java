/*
package com.spj.authorization.security.endpoints;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

*/
/**
 * @author Yogesh Sharma
 *//*

@RestController
@RequestMapping(value = "oauth", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class OAuthController {

    @RequestMapping(value = {"user"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> user(OAuth2Authentication user) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user", user.getUserAuthentication().getPrincipal());
        userInfo.put("authorities", AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));

        return userInfo;
    }
}
*/
