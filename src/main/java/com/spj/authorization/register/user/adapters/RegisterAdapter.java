package com.spj.authorization.register.user.adapters;

import com.spj.authorization.register.user.dao.IUserDao;
import com.spj.authorization.register.user.ports.in.IRegisterAdapter;
import com.spj.authorization.security.entities.Authorities;
import com.spj.authorization.security.entities.User;
import com.spj.authorization.security.pojo.UserType;
import com.spj.authorization.security.repository.AuthoritiesRepository;
import com.spj.authorization.security.repository.UserRepository;
import com.spj.register.openapi.resources.RegisterBarberRequest;
import com.spj.register.openapi.resources.RegisterBarberResponse;
import com.spj.register.openapi.resources.RegisterCustomerRequest;
import com.spj.register.openapi.resources.RegisterCustomerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterAdapter implements IRegisterAdapter {

    private final IUserDao userDao;
    private final AuthoritiesRepository authoritiesRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RegisterMapper registerMapper;

    /**
     * This method registers all types of users available in userType
     */
    private User register(User user, UserType userType) {
        if (CollectionUtils.isEmpty(userDao.searchUserWithEmailAndAuthority(user.getEmail(), userType.getResponse()))) {

            Authorities auth = authoritiesRepository.findByAuthority(userType.getResponse());
            user.setAuthorityId(auth.getAuthorityId());

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.saveAndFlush(user);

            log.debug("User registered successfully {}", user.getEmail());
            //TODO: put the message in a queue
            //CompletableFuture.runAsync(() -> myEmailAdapter.sendOtpMessage(user.getEmail()));

            log.info("Call completed successfully for user {}", user.getEmail());
            return user;
        }

        log.error("User Already Exists for email {}", user.getEmail());
        throw new DuplicateKeyException("User already Exists");
    }

    @Override
    public RegisterCustomerResponse registerCustomer(RegisterCustomerRequest registerCustomerRequest) {
        return registerMapper.toResponse(
                register(registerMapper.toEntity(registerCustomerRequest), UserType.CUSTOMER));
    }

    @Override
    public RegisterBarberResponse registerBarber(RegisterBarberRequest registerBarberRequest) {
        return registerMapper.toBarberResponse(
                register(registerMapper.toEntity(registerBarberRequest), UserType.BARBER));
    }
}
