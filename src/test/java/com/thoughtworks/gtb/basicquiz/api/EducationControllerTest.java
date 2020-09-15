package com.thoughtworks.gtb.basicquiz.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class EducationControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void should_return_educations_when_get_education_given_user_id() throws Exception {
        mockMvc.perform(get("/users/1/educations"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(status().isOk());
    }
}