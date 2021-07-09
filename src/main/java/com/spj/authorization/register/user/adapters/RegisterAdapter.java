package com.spj.authorization.register.user.adapters;

import com.spj.authorization.register.user.ports.in.IRegisterAdapter;
import com.spj.authorization.security.entities.User;
import com.spj.authorization.security.repository.UserRepository;
import com.spj.register.openapi.resources.RegisterUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterAdapter implements IRegisterAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public RegisterUserResponse registerUser(User userRegisterPayload) {
        userRegisterPayload.setAuthorityId(userRegisterPayload.getAuthorityId());
        userRegisterPayload.setPassword(passwordEncoder.encode(userRegisterPayload.getPassword()));
        userRepository.saveAndFlush(userRegisterPayload);

        log.debug("User registered successfully {}", userRegisterPayload.getEmail());
        return new RegisterUserResponse().userName(userRegisterPayload.getEmail()).message("Registered");
    }

    @Override
    public RegisterUserResponse updatePassword(User userRegisterPayload) {
        User user = userRepository.findByEmail(userRegisterPayload.getEmail());

        user.setPassword(passwordEncoder.encode(userRegisterPayload.getPassword()));
        user.setFailedLoginAttempt(0);
        user.setLocked(false);
        userRepository.saveAndFlush(user);

        log.debug("User registered successfully {}", user.getEmail());
        return new RegisterUserResponse().userName(userRegisterPayload.getEmail()).message("Password Updated");
    }
}
