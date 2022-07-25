package com.cts.authorization.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JwtTokenUtilTest {

    UserDetails userDetails;

    @Autowired
    private JwtTokenUtil util;

    @Test
    void testGenerateTokenWithRandomUserGeneratesNull() {
        UserDetails details = new org.springframework.security.core.userdetails.User("Hacker", "pass",
                new ArrayList<>());
        assertThat(util.generateToken(details)).isNotNull();
    }

    @Test
    void validateTokenTest() {
        userDetails = new User("roy", "123", new ArrayList<>());
        String generateToken = util.generateToken(userDetails);
        Boolean validateToken = util.validateToken(generateToken, userDetails);
        assertThat(validateToken).isTrue();
    }
}
