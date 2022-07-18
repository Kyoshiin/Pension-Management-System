package com.cts.authorization.service;

import com.cts.authorization.model.UserModel;
import com.cts.authorization.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class JwtUserDetailsServiceTest {

    @Mock
    private UserRepository userDao;

    @InjectMocks
    private JwtUserDetailsService service;


    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void loadUserByUserNameShouldThrowExceptionTest() {
        when(userDao.findByUserName("wrongUserName")).thenReturn(null);
        assertThatThrownBy(() -> service.loadUserByUsername("wrongUserName"))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessage("User not found with username: wrongUserName");
        verify(userDao, Mockito.times(1)).findByUserName("wrongUserName");
    }

    @Test
    void loadUserByUserNameShouldUserNameTest() {
        when(userDao.findByUserName("admin")).thenReturn(new UserModel(1, "admin", "pass"));
        assertThat(service.loadUserByUsername("admin")).isNotNull();
        verify(userDao, Mockito.times(1)).findByUserName("admin");
    }

}
