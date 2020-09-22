package com.thoughtworks.gtb.basicquiz.api;

import com.thoughtworks.gtb.basicquiz.domain.Education;
import com.thoughtworks.gtb.basicquiz.exception.UserHasNotEducationException;
import com.thoughtworks.gtb.basicquiz.service.EducationService;
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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EducationController.class)
@AutoConfigureJsonTesters
class EducationControllerTest {

    @MockBean
    private UserService userService;
    @MockBean
    private EducationService educationService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<List<Education>> educationsJacksonTester;
    @Autowired
    private JacksonTester<Education> educationJacksonTester;

    private List<Education> educations = new ArrayList<>();



    @BeforeEach
    public void beforeEach() {
        Education firstEducation = Education.builder()
                .description("demo test is good")
                .title("education test")
                .year(2000)
                .build();
        Education secondEducation = Education.builder()
                .description("demo test is good second")
                .title("education second test")
                .year(2002)
                .build();
        educations.add(firstEducation);
        educations.add(secondEducation);
    }

    @AfterEach
    public void afterEach() {
        Mockito.reset(educationService);
        Mockito.reset(userService);
    }

    @Nested
    class getEducationByUserId{
        @Test
        public void should_get_educations_when_given_user_id() throws Exception {
            when(educationService.getEducationByUserId(123L)).thenReturn(educations);
            MockHttpServletResponse response = mockMvc.perform(get("/users/{id}/educations", 123))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse();
            assertThat(response.getContentAsString()).isEqualTo(
                    educationsJacksonTester.write(educations).getJson()
            );
            verify(educationService).getEducationByUserId(123L);
        }
        @Test
        public void should_throw_execption_when_given_wrong_userId() throws Exception {
            when(educationService.getEducationByUserId(123L)).thenThrow(new UserHasNotEducationException());
            mockMvc.perform(get("/users/{id}/educations", 123))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.message", containsString("该用户没有教育经历")));
            verify(educationService).getEducationByUserId(123L);
        }
    }

    @Nested
    class createEducationByUserId{
        @Test
        public void should_return_new_educations_when_given_uuer_id_and_eduaction() throws Exception {
            when(educationService.createEducationByUserId(123L, educations.get(0))).thenReturn(educations);
            MockHttpServletResponse response = mockMvc.perform(post("/users/{id}/educations", 123)
                    .content(educationJacksonTester.write(educations.get(0)).getJson())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andReturn()
                    .getResponse();
            assertThat(response.getContentAsString()).isEqualTo(
                    educationsJacksonTester.write(educations).getJson()
            );
            verify(educationService).createEducationByUserId(123L, educations.get(0));
        }
    }
}