package com.thoughtworks.gtb.basicquiz.service;

import com.thoughtworks.gtb.basicquiz.domain.Education;
import com.thoughtworks.gtb.basicquiz.domain.User;
import com.thoughtworks.gtb.basicquiz.exception.UserHasNotEducationException;
import com.thoughtworks.gtb.basicquiz.exception.UserIsNotFoundException;
import com.thoughtworks.gtb.basicquiz.repo.EducationRepo;
import com.thoughtworks.gtb.basicquiz.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {
    UserService userService;

    @MockBean
    UserRepo userRepo;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepo);
    }

    @Test
    void should_throw_user_is_not_found_exception_when_service_getuser_given_wrong_id() {
        when(userRepo.findById(anyInt())).thenReturn(null);
        assertThrows(
                UserIsNotFoundException.class,
                () -> {
                    userService.getUserById(1);
                });
    }

    @Test
    void should_get_user_success_when_service_getusers_given_id() throws Exception {
        User user = User.builder().id(1).age(14).avatar("dd").description("demo").name("wjl").build();
        when(userRepo.findById(1)).thenReturn(user);
        assertEquals(userService.getUserById(1), user);
    }

    @Test
    void should_create_user_success_when_service_create_user_given_new_user()  {
        User user = User.builder().id(1).age(14).avatar("dd").description("demo").name("wjl").build();
        userService.createUser(user);
        verify(userRepo).save(user);
    }
}