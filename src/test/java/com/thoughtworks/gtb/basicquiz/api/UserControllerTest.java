package com.thoughtworks.gtb.basicquiz.api;

import com.thoughtworks.gtb.basicquiz.domain.User;
import com.thoughtworks.gtb.basicquiz.exception.UserHasNotEducationException;
import com.thoughtworks.gtb.basicquiz.exception.UserIsNotFoundException;
import com.thoughtworks.gtb.basicquiz.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureJsonTesters
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @Autowired
    private JacksonTester<User> userJacksonTester;

    private User firstUser;
    @BeforeEach
    public void beforeEach() {
        firstUser = User.builder().avatar("http:dd").age(18).description("demo test dd").name("wjl test").build();
    }

    @AfterEach
    public void afterEach() {
        Mockito.reset(userService);
    }

    @Nested
    class getUser{
        @Test
        public void should_getUser_when_given_userId() throws Exception {
            when(userService.getUserById(123L)).thenReturn(firstUser);
            MockHttpServletResponse response = mockMvc.perform(get("/users/{id}", 123))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse();
            assertThat(response.getContentAsString()).isEqualTo(
                    userJacksonTester.write(firstUser).getJson()
            );
            verify(userService).getUserById(123L);
        }
        @Test
        public void should_throw_execption_when_given_wrong_userId() throws Exception {
            when(userService.getUserById(123L)).thenThrow(new UserIsNotFoundException());
            mockMvc.perform(get("/users/{id}", 123))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.message", containsString("找不到用户")));
            verify(userService).getUserById(123L);
        }
    }

    @Nested
    class createUser{
        @Test
        public void should_return_new_user_when_given_user_info() throws Exception {
            when(userService.createUser(firstUser)).thenReturn(firstUser);
            MockHttpServletResponse response = mockMvc.perform(post("/users").content(userJacksonTester.write(firstUser).getJson())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andReturn()
                    .getResponse();
            assertThat(response.getContentAsString()).isEqualTo(
                    userJacksonTester.write(firstUser).getJson()
            );
            verify(userService).createUser(firstUser);
        }
    }
}