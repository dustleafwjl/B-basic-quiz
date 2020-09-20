package com.thoughtworks.gtb.basicquiz.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.gtb.basicquiz.domain.User;
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
    public void should_return_user_info_when_get_users_given_id() throws Exception {
        String avatar = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1600151586026&di=b45aa215fde87578c6cfafc3f12b391c&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201706%2F10%2F20170610192627_yhAMN.thumb.700_0.jpeg";

        String jsonStudent = "{\"name\": \"KAMIL\", \"age\": 24, \"avatar\": \""+ avatar +"\", \"description\": \"description should in 8 - 1024\"}";
        String saveUser = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStudent))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(get("/users/"+objectMapper.readValue(saveUser, User.class).getId()))
                .andExpect(jsonPath("$.name", is("KAMIL")))
                .andExpect(jsonPath("$.age", is(24)))
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_not_found_when_get_users_given_wrong_id() throws Exception {
        mockMvc.perform(get("/users/32"))
                .andExpect(jsonPath("$.message", is("找不到用户")))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_return_user_info_when_post_users_given_new_user() throws Exception {
        String avatar = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1600151586026&di=b45aa215fde87578c6cfafc3f12b391c&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201706%2F10%2F20170610192627_yhAMN.thumb.700_0.jpeg";

        String jsonStudent = "{\"name\": \"Tom\", \"age\": 18, \"avatar\": \""+ avatar +"\", \"description\": \"description should in 8 - 1024\"}";
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStudent))
                .andExpect(jsonPath("$.name", is("Tom")))
                .andExpect(jsonPath("$.age", is(18)))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_return_bad_request_when_post_users_given_new_user_has_wrong_name() throws Exception {
        String avatar = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1600151586026&di=b45aa215fde87578c6cfafc3f12b391c&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201706%2F10%2F20170610192627_yhAMN.thumb.700_0.jpeg";

        String jsonStudent = "{\"age\": 18, \"avatar\": \""+ avatar +"\", \"description\": \"description should in 8 - 1024\"}";
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStudent))
                .andExpect(jsonPath("$.message", is("用户名不能为空")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_return_bad_request_when_post_users_given_new_user_has_wrong_description() throws Exception {
        String avatar = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1600151586026&di=b45aa215fde87578c6cfafc3f12b391c&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201706%2F10%2F20170610192627_yhAMN.thumb.700_0.jpeg";

        String jsonStudent = "{\"name\": \"demo\", \"age\": 18, \"avatar\": \""+ avatar +"\", \"description\": \"desc\"}";
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStudent))
                .andExpect(jsonPath("$.message", is("描述字段格式不正确")))
                .andExpect(status().isBadRequest());
    }
}