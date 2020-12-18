package com.spj.authorization.security.adapters;

import com.spj.authorization.security.entities.User;
import com.spj.authorization.security.pojo.GoogleOAuth2UserInfo;
import com.spj.authorization.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOidcUserAdapter extends OidcUserService {
    private final UserRepository userRepository;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        Map<String, Object> attributes = oidcUser.getAttributes();
        GoogleOAuth2UserInfo userInfo = GoogleOAuth2UserInfo.builder()
                .email((String) attributes.get("email"))
                .id((String) attributes.get("sub"))
                .imageUrl((String) attributes.get("picture"))
                .name((String) attributes.get("name"))
                .build();

        updateUser(userInfo);
        return oidcUser;
    }

    private void updateUser(GoogleOAuth2UserInfo userInfo) {
        User user = userRepository.findByEmail(userInfo.getEmail());
        if(user == null) {
            user = new User();
        }
        user.setEmail(userInfo.getEmail());
        user.setImageUrl(userInfo.getImageUrl());
        user.setName(userInfo.getName());
        user.setSocialLoginType("google");
        userRepository.save(user);
    }
}