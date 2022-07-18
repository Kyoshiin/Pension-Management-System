package com.cts.authorization.repository;

import com.cts.authorization.model.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@DataJpaTest
class UserDaoTest {

    @MockBean
    private UserRepository dao;

    @Test
    void testUserDaoFindByName() {
        UserModel user = new UserModel(1, "admin", "password");
        when(dao.findByUserName("admin")).thenReturn(user);
        assertThat(dao.findByUserName("admin").equals(user));
    }

}
