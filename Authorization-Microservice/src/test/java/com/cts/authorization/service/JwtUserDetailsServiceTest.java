package com.cts.authorization.service;

import com.cts.authorization.model.UserModel;
import com.cts.authorization.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class JwtUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private JwtUserDetailsService service;


    @BeforeEach
    void setUp() {
    }

    @Test
    void loadUserByUserNameShouldThrowExceptionTest() {
        when(userRepository.findByUserName("wrongUserName")).thenReturn(null);
        assertThatThrownBy(() -> service.loadUserByUsername("wrongUserName"))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessage("User not found with username: wrongUserName");
        verify(userRepository).findByUserName("wrongUserName");
    }

    @Test
    void loadUserByUserNameShouldUserNameTest() {
        when(userRepository.findByUserName("roy")).thenReturn(new UserModel(1, "roy", "123"));
        assertThat(service.loadUserByUsername("roy")).isNotNull();
        verify(userRepository).findByUserName("roy"); // for checking if mock service was called
    }

}
