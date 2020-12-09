package com.spj.authorization.security.config;

import com.spj.authorization.security.adapters.CustomUserDetailsAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsAdapter userBarberFacade;
    private final OidcUserService oidcUserService;

    @Override
    protected final void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userBarberFacade).passwordEncoder(passwordEncoder);
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .cors()
                .and()
                    .csrf().disable().formLogin()
                /*.and()
                    .antMatcher("/**").authorizeRequests()
                    .antMatchers("/findmysalon/oauth/authorize/**").permitAll()
                    .anyRequest().authenticated()*/
                .and()
                    .oauth2Login()
                    .authorizationEndpoint().baseUri("/oauth2/authorize")
                        .authorizationRequestRepository(customAuthorizationRequestRepository())
                    .and()
                        .redirectionEndpoint().baseUri("/oauth2/callback/*")
                .and()
                    .userInfoEndpoint().oidcUserService(oidcUserService);
    }

    @Bean
    public AuthorizationRequestRepository customAuthorizationRequestRepository() {
        return new HttpSessionOAuth2AuthorizationRequestRepository();
    }
}