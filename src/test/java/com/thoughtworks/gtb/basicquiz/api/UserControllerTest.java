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
    public void should_return_user_info_when_get_users_given_id() throws Exception {
        mockMvc.perform(get("/users/1"))
                .andExpect(jsonPath("$.name", is("KAMIL")))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.age", is(24)))
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_user_info_when_post_users_given_new_user() throws Exception {
        String avatar = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1600151586026&di=b45aa215fde87578c6cfafc3f12b391c&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201706%2F10%2F20170610192627_yhAMN.thumb.700_0.jpeg";

        String jsonStudent = "{\"name\": \"Tom\", \"age\": 18, \"avatar\": \""+ avatar +"\", \"description\": \"\"}";
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStudent))
                .andExpect(jsonPath("$.name", is("Tom")))
                .andExpect(jsonPath("$.age", is(18)))
                .andExpect(status().isCreated());
    }
}