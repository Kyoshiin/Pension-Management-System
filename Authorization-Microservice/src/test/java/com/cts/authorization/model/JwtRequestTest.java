package com.cts.authorization.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JwtRequestTest {

	private AuthenticationRequest authenticationRequest = new AuthenticationRequest("admin", "password");

	@Test
	void testUserNameGetter() {
		assertThat(authenticationRequest.getUserName().equals("admin")).isTrue();
	}
	
	@Test
    void testPensionerBean() {
        final BeanTester beanTester = new BeanTester();
        beanTester.getFactoryCollection();
        beanTester.testBean(AuthenticationRequest.class);
    }


}
