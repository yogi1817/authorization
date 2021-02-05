package com.spj.authorization.register.user.adapters;

import com.spj.authorization.register.user.messaging.UserRegisterPayload;
import com.spj.authorization.register.user.ports.in.IRegisterAdapter;
import com.spj.authorization.security.entities.User;
import com.spj.authorization.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterAdapter implements IRegisterAdapter {

    //private final IUserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RegisterMapper registerMapper;

    @Override
    public void registerUser(UserRegisterPayload userRegisterPayload) {
        User user = registerMapper.toEntity(userRegisterPayload);
        user.setAuthorityId(userRegisterPayload.getAuthorityId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);

        log.debug("User registered successfully {}", user.getEmail());
    }

    @Override
    public void updatePassword(UserRegisterPayload userRegisterPayload) {
        User user = userRepository.findByEmail(userRegisterPayload.getEmail());

        user.setPassword(passwordEncoder.encode(userRegisterPayload.getPassword()));
        userRepository.saveAndFlush(user);

        log.debug("User registered successfully {}", user.getEmail());
    }
}
