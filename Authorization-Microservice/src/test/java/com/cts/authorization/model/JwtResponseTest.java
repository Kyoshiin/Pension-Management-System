package com.cts.authorization.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JwtResponseTest {
	
	private AuthenticationResponse jwtResArg;

	@BeforeEach
	void setUp() throws Exception {
		
		jwtResArg = new AuthenticationResponse("token");
	}

	@Test
	void test() {
		assertThat(jwtResArg.getToken().equals("token"));
	}

}
