package com.cts.authorization.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserModelTest {

	private UserModel user1;
	private UserModel user2;
	

	@BeforeEach
	void setUp() throws Exception {
		
		user1 = new UserModel();
		user2 = new UserModel(1, "user", "pass");
	}
	
	@Test
    void testPensionerBean() {
        final BeanTester beanTester = new BeanTester();
        beanTester.getFactoryCollection();
        beanTester.testBean(UserModel.class);
    }
}
