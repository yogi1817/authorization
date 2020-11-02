package com.spj.authorization.security.adapters;

import com.spj.authorization.security.entities.Authorities;
import com.spj.authorization.security.entities.User;
import com.spj.authorization.security.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsAdapterTest {
    private CustomUserDetailsAdapter testSubject;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        testSubject = new CustomUserDetailsAdapter(userRepository);
    }

    @Test
    void shouldLoadUserByUserNameAndReturnUserDetails() {
        Authorities authority = Authorities.builder()
                .authority("BARBER")
                .authorityId(1L)
                .build();
        User user = User.builder()
                .email("email@test.com")
                .password("testpassword")
                .authority(authority)
                .build();

        doReturn(user)
                .when(userRepository)
                .findByEmail("email@test.com");

        UserDetails testResult = testSubject.loadUserByUsername("email@test.com");

        assertThat(testResult != null);
        verify(userRepository, times(1))
                .findByEmail("email@test.com");
        verifyNoMoreInteractions(userRepository);
    }
}