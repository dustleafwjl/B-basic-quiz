package com.thoughtworks.gtb.basicquiz.api;

import com.thoughtworks.gtb.basicquiz.domain.Education;
import com.thoughtworks.gtb.basicquiz.service.EducationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users/{id}/educations")
public class EducationController {

    private final EducationService educationService;

    EducationController(EducationService educationService) {
        this.educationService = educationService;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Education> getEducationByUserId(@PathVariable long id) {
        return educationService.getEducationByUserId(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Education> createEducationByUserId(@PathVariable long id, Education education) {
        return educationService.createEducationByUserId(id, education);
    }
}
