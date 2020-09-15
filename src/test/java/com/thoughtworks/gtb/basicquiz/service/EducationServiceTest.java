package com.thoughtworks.gtb.basicquiz.service;

import com.thoughtworks.gtb.basicquiz.domain.Education;
import com.thoughtworks.gtb.basicquiz.exception.UserHasNotEducationException;
import com.thoughtworks.gtb.basicquiz.repo.EducationRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class EducationServiceTest {

    EducationService educationService;

    @MockBean
    EducationRepo educationRepo;

    @BeforeEach
    void setUp() {
        educationService = new EducationService(educationRepo);
    }

    @Test
    void should_throw_user_has_not_deucation_exception_when_service_getEducation_given_wrong_user_id() {
        when(educationRepo.findByUserId(anyInt())).thenReturn(new ArrayList<Education>());
        assertThrows(
            UserHasNotEducationException.class,
            () -> {
                educationService.getEducationByUserId(1);
            });
    }

    @Test
    void should_get_educations_success_when_service_getEducation_given_user_id() throws UserHasNotEducationException {
        List<Education> educations = new ArrayList<Education>(){{
            add(Education.builder().id(1).description("ddd").title("demo title").userId(1).year(1201).build());
            add(Education.builder().id(2).description("ddd").title("demo title").userId(1).year(1201).build());
        }};
        when(educationRepo.findByUserId(1)).thenReturn(educations);
        assertEquals(educationService.getEducationByUserId(1), educations);
    }

    @Test
    void should_create_educations_success_when_service_getEducation_given_user_id_and_new_education() throws UserHasNotEducationException {
        Education mockEducation = Education.builder().id(1).description("ddd").userId(1).year(1201).build();
        educationService.createEducationByUserId(1, mockEducation);
        verify(educationRepo).save(mockEducation);
    }

}