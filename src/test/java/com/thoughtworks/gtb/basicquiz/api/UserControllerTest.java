package com.thoughtworks.gtb.basicquiz.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void should_return_user_info_when_get_users_givent_id() throws Exception {
        mockMvc.perform(get("/users/1"))
                .andExpect(jsonPath("$.name", is("王江林")))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.age", is(18)))
                .andExpect(status().isOk());
    }
}