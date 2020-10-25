package com.spj.authorization.security.adapters;

import com.spj.authorization.security.entities.OAuthClientDetails;
import com.spj.authorization.security.repository.OAuthClientsDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomClientDetailsAdapter implements ClientDetailsService {

    private final OAuthClientsDetailsRepository oAuthClientsDetailsRepository;

    @Override
    @Cacheable("ClientDetails")
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        OAuthClientDetails client =
                oAuthClientsDetailsRepository.findByClientId(clientId);

        if(client == null)
            return new BaseClientDetails();

        BaseClientDetails base = new BaseClientDetails(client.getClientId(), client.getResourceIds(),
                client.getScope(), client.getAuthorizedGrantTypes(),  client.getAuthorities());
        base.setClientSecret(client.getClientSecret());
        base.setAccessTokenValiditySeconds(client.getAccessTokenValidity());
        base.setRefreshTokenValiditySeconds(client.getRefreshTokenValidity());
        base.setRegisteredRedirectUri(Set.of(client.getWebServerRedirectUri()));
        return base;
    }
}
