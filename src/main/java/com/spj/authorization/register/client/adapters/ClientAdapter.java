package com.spj.authorization.register.client.adapters;

import com.spj.authorization.configs.ServiceConfig;
import com.spj.authorization.register.client.ports.in.IClientAdapter;
import com.spj.authorization.security.entities.OAuthClientDetails;
import com.spj.authorization.security.pojo.UserType;
import com.spj.authorization.security.repository.OAuthClientsDetailsRepository;
import com.spj.register.openapi.resources.RegisterClientRequest;
import com.spj.register.openapi.resources.RegisterClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientAdapter implements IClientAdapter {

    private final ServiceConfig serviceConfig;
    private final OAuthClientsDetailsRepository oAuthClientsDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterClientResponse registerClient(RegisterClientRequest registerClientRequest) {
        OAuthClientDetails oAuthClientDetails = OAuthClientDetails.builder()
                .accessTokenValidity(serviceConfig.getAccessTokenValidity())
                .additionalInformation(null)
                .authorities(UserType.CUSTOMER.name())
                .authorizedGrantTypes("authorization_code,refresh_token")
                .autoapprove(null)
                .clientId(registerClientRequest.getClientName())
                .clientSecret(passwordEncoder.encode(registerClientRequest.getClientPassword()))
                .refreshTokenValidity(serviceConfig.getRefreshTokenValidity())
                .resourceIds(null)
                .scope("read")
                .webServerRedirectUri(registerClientRequest.getRedirectUri())
                .build();

        oAuthClientsDetailsRepository.saveAndFlush(oAuthClientDetails);

        return new RegisterClientResponse().clientName(registerClientRequest.getClientName())
                .message("Customer added");
    }
}
