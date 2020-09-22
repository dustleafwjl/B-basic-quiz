package com.thoughtworks.gtb.basicquiz.service;

import com.thoughtworks.gtb.basicquiz.domain.User;
import com.thoughtworks.gtb.basicquiz.exception.UserIsNotFoundException;
import com.thoughtworks.gtb.basicquiz.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// GTB: 可以看看不用 @SpringBootTest 行不行？
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    UserService userService;

    // GTB: + 用到了@MockBean，不错，高级！
    @Mock
    UserRepository userRepo;

    private User user;
    @BeforeEach
    void setUp() {
        userService = new UserService(userRepo);
        user = User.builder()
                .id(123L)
                .name("daiv")
                .age(18L)
                .avatar("httl:demotest")
                .description("lt is user test").build();
    }

    @Nested
    class getUerById{
        @Test
        void should_throw_user_is_not_found_exception_when_service_getuser_given_wrong_id() throws Exception {
            when(userRepo.findById(123L)).thenReturn(Optional.empty());
            UserIsNotFoundException userIsNotFoundException = assertThrows(UserIsNotFoundException.class,
                    () -> {
                        userService.getUserById(123L);
                    });
        }

        @Test
        void should_get_user_success_when_service_getusers_given_id() throws Exception {
            when(userRepo.findById(123L)).thenReturn(Optional.of(user));
            assertEquals(userService.getUserById(123L), user);
        }
    }

    @Nested
    class createUser {
        @Test
        void should_create_user_success_when_service_create_user_given_new_user()  {
            userService.createUser(user);
            verify(userRepo).save(user);
        }
    }
}