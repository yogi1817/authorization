package com.spj.authorization.security.config;

import com.spj.authorization.security.adapters.CustomClientDetailsAdapter;
import com.spj.authorization.security.entities.User;
import com.spj.authorization.security.pojo.CustomUser;
import com.spj.authorization.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
@Slf4j
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    private AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final CustomClientDetailsAdapter customClientDetailsAdapter;
    private final UserRepository userRepository;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(customClientDetailsAdapter);
    }

    @EventListener
    public void authSuccessEventListener(AuthenticationSuccessEvent authorizedEvent){
        if(authorizedEvent.getAuthentication().getPrincipal() instanceof CustomUser){
            CustomUser customUser = (CustomUser) authorizedEvent.getAuthentication().getPrincipal();
            log.debug("login Successful for user {}", customUser.getEmail());
            if(customUser.getFailedLoginAttempt()>0){
                User user = userRepository.findByEmail(customUser.getEmail());
                user.setFailedLoginAttempt(0);
                user.setLocked(false);
                userRepository.saveAndFlush(user);
            }
        }
    }

    @EventListener
    public void authFailedEventListener(AbstractAuthenticationFailureEvent oAuth2AuthenticationFailureEvent){
        if(oAuth2AuthenticationFailureEvent.getAuthentication().getPrincipal() instanceof CustomUser){
            String userId = oAuth2AuthenticationFailureEvent.getAuthentication().getPrincipal().toString();
            log.error("Failed login attempt for user {}",userId);
            User user = userRepository.findByEmail(userId);
            user.setFailedLoginAttempt(user.getFailedLoginAttempt()+1);
            if(user.getFailedLoginAttempt()>=5 && !user.isLocked()) {
                user.setLocked(true);
                log.error("Account locked for user Id {}",userId);
            }

            userRepository.saveAndFlush(user);
        }
    }
}
