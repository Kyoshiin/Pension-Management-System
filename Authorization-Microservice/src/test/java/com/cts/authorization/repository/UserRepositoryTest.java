package com.cts.authorization.repository;

import com.cts.authorization.model.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@DataJpaTest
class UserRepositoryTest {

    @MockBean
    private UserRepository repo;

    @Test
    void testUserRepoFindByName() {
        UserModel user = new UserModel(1, "roy", "123");
        when(repo.findByUserName("roy")).thenReturn(user);
        assertThat(repo.findByUserName("roy").equals(user));
    }

}
