package com.cts.authorization.controller;

import com.cts.authorization.config.JwtTokenUtil;
import com.cts.authorization.model.UserModel;
import com.cts.authorization.service.JwtUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class JwtAuthenticationControllerTest {

    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private JwtTokenUtil jwtTokenUtil;
    @MockBean
    private JwtUserDetailsService userDetailsService;
    @InjectMocks
    private JwtAuthenticationController controller;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAuthorization() throws Exception {
        when(jwtTokenUtil.getUsernameFromToken("Bearer token")).thenReturn(null);
        assertThat(controller.validateRequest("Bearer token")).isFalse();
    }

    @Test
    public void testAuthorizationInvalid() throws Exception {
        UserModel user = new UserModel(1, "admin", "pass");
        UserDetails details = new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                new ArrayList<>());
        when(userDetailsService.loadUserByUsername("admin")).thenReturn(details);
        when(jwtTokenUtil.getUsernameFromToken("token")).thenReturn("admin");
        assertThat(controller.validateRequest("token")).isFalse();

    }

    @Test
    public void testAuthorizationNullUser() throws Exception {

        UserModel user = new UserModel(1, "admin", "pass");
        UserDetails details = new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                new ArrayList<>());
        when(userDetailsService.loadUserByUsername("admin")).thenReturn(details);
        when(jwtTokenUtil.getUsernameFromToken("token")).thenReturn("admin");

        assertThat(controller.validateRequest("WrongToken")).isFalse();

    }
}
