package com.thoughtworks.gtb.basicquiz.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[0].title", is("First level graduation in Graphic Design")))
                .andExpect(jsonPath("$[1].userId", is(1)))
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_not_found_when_get_education_given_wrong_user_id() throws Exception {
        mockMvc.perform(get("/users/36/educations"))
                .andExpect(jsonPath("$.message",is("该用户没有教育经历")))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_return_educations_when_post_education_given_new_education_and_user_id() throws Exception {
        String jsonStudent = "{\"description\": \"Eos, explicabo\",\"year\": 2011, \"title\": \"Secondary school specializing in artistic\"}";
        mockMvc.perform(post("/users/1/educations")
                .content(jsonStudent)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[1].userId", is(1)))
                .andExpect(jsonPath("$[2].userId", is(1)))
                .andExpect(jsonPath("$[2].title", is("Secondary school specializing in artistic")))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_return_bad_request_when_post_education_given_new_education_has_not_title() throws Exception {
        String jsonStudent = "{\"description\" : \"Eos, explicabo\", \"year\": 2011}";
        mockMvc.perform(post("/users/1/educations")
                .content(jsonStudent)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("标题不能为空")))
                .andExpect(status().isBadRequest());
    }
}