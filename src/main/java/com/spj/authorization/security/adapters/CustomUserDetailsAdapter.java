package com.spj.authorization.security.adapters;

import com.spj.authorization.security.entities.User;
import com.spj.authorization.security.pojo.CustomUser;
import com.spj.authorization.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountLockedException;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsAdapter implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Cacheable("UserDetails")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        try {
            user = userRepository.findByEmail(username);
            if (user != null) {
                return new CustomUser(user);
            } else {
                throw new UsernameNotFoundException("User " + username + " was not found in the database");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
    }
}
