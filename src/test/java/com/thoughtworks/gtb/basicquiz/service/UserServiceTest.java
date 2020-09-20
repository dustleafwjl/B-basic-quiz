package com.thoughtworks.gtb.basicquiz.service;

import com.thoughtworks.gtb.basicquiz.domain.User;
import com.thoughtworks.gtb.basicquiz.exception.UserIsNotFoundException;
import com.thoughtworks.gtb.basicquiz.repository.UserRepo;
import com.thoughtworks.gtb.basicquiz.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// GTB: 可以看看不用 @SpringBootTest 行不行？
@SpringBootTest
class UserServiceTest {
    UserService userService;

    // GTB: + 用到了@MockBean，不错，高级！
    @MockBean
    UserRepository userRepo;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepo);
    }

    @Test
    void should_throw_user_is_not_found_exception_when_service_getuser_given_wrong_id() {
        when(userRepo.findById(anyLong())).thenReturn(null);
        assertThrows(
                UserIsNotFoundException.class,
                () -> {
                    userService.getUserById(1);
                });
    }

    @Test
    void should_get_user_success_when_service_getusers_given_id() throws Exception {
        User user = User.builder().id(1).age(14).avatar("dd").description("demo").name("wjl").build();
        when(userRepo.findById((long) 1)).thenReturn(java.util.Optional.ofNullable(user));
        assertEquals(userService.getUserById(1), user);
    }

    @Test
    void should_create_user_success_when_service_create_user_given_new_user()  {
        User user = User.builder().id(1).age(14).avatar("dd").description("demo").name("wjl").build();
        userService.createUser(user);
        verify(userRepo).save(user);
    }
}