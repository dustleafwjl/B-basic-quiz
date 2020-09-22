package com.thoughtworks.gtb.basicquiz.repository;

import com.thoughtworks.gtb.basicquiz.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @AfterEach
    public void beforeEach() {
        testEntityManager.clear();
    }

    @Test
    public void should_return_user_when_id_exits() {
        User user = testEntityManager.persistAndFlush(User.builder()
                            .name("kiven").age(18).avatar("http:12312")
                            .description("demo test description").build());

        Optional<User> foundUser = userRepository.findById(user.getId());
        assertThat(foundUser.isPresent()).isTrue();
        assertThat(foundUser.get()).isEqualTo(User.builder().id(user.getId())
                .name("kiven").age(18).avatar("http:12312")
                .description("demo test description").build());
    }

}