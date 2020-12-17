package com.spj.authorization.security.adapters;

import com.spj.authorization.configs.ServiceConfig;
import com.spj.authorization.security.entities.OAuthApprovals;
import com.spj.authorization.security.pojo.CustomUser;
import com.spj.authorization.security.repository.OAuthApprovalsRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthorizationCodeDetailsAdapter implements AuthorizationCodeServices {

    private final OAuthApprovalsRepository oAuthApprovalsRepository;
    private final ServiceConfig serviceConfig;
    private RandomValueStringGenerator generator = new RandomValueStringGenerator();

    @Override
    public String createAuthorizationCode(OAuth2Authentication authentication) {
        CustomUser customUser = (CustomUser) authentication.getPrincipal();

        generator.setLength(45);
        String code = generator.generate();
        OAuthApprovals oAuthApprovals = OAuthApprovals.builder()
                .active(true)
                .clientId(authentication.getOAuth2Request().getClientId())
                .expiresAt(LocalDateTime.now().plusMinutes(10))
                .lastModifiedAt(LocalDateTime.now())
                .scope(serviceConfig.getClientScope())
                .userId(customUser.getEmail())
                .code(code)
                .token(SerializationUtils.serialize(authentication))
                .build();
        oAuthApprovalsRepository.saveAndFlush(oAuthApprovals);
        return code;
    }

    @SneakyThrows
    @Override
    public OAuth2Authentication consumeAuthorizationCode(String code) throws InvalidGrantException {
        OAuth2Authentication oAuth2Authentication;
        OAuthApprovals oAuthApprovals = oAuthApprovalsRepository.findByCode(code);
        if (oAuthApprovals != null
                && oAuthApprovals.getExpiresAt().isAfter(LocalDateTime.now())
                && oAuthApprovals.isActive()) {
            oAuth2Authentication = SerializationUtils.deserialize(oAuthApprovals.getToken());
            oAuthApprovals.setActive(false);
            oAuthApprovals.setLastModifiedAt(LocalDateTime.now());
            oAuthApprovalsRepository.saveAndFlush(oAuthApprovals);
            return oAuth2Authentication;
        }
        return null;
    }
}
