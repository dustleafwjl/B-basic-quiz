package com.thoughtworks.gtb.basicquiz.repository;

import com.thoughtworks.gtb.basicquiz.domain.Education;
import com.thoughtworks.gtb.basicquiz.domain.User;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class EducationRepositoryTest {

    @Autowired
    private EducationRepository educationRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    private User user;

    @BeforeEach
    public void beforeEach() {
        user = User.builder()
                .name("kiven").age(18).avatar("http:12312")
                .description("demo test description").build();
        user = testEntityManager.persistAndFlush(this.user);
        testEntityManager.persistAndFlush(Education.builder()
                .description("education")
                .title("eudcation one")
                .year(2002)
                .user(user).build());
        testEntityManager.persistAndFlush(Education.builder()
                .description("education")
                .title("eudcation one")
                .year(2002)
                .user(user).build());
    }

    @AfterEach
    public void afterEach() {
        testEntityManager.clear();
    }


    @Test
    void findAllByUserId() {
        List<Education> foundEducation = educationRepository.findAllByUserId(user.getId());
        assertThat(foundEducation.size()).isEqualTo(2);
    }

    @Test
    void findAll() {
        User sencondUser = User.builder()
                .name("kiven").age(18).avatar("http:12312")
                .description("demo test description").build();
        User saveUser = testEntityManager.persistAndFlush(sencondUser);
        testEntityManager.persistAndFlush(Education.builder()
                .description("education")
                .title("eudcation one")
                .year(2002)
                .user(saveUser).build());
        List<Education> foundEducation = educationRepository.findAll();
        assertThat(foundEducation.size()).isEqualTo(3);
    }
}