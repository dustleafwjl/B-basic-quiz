package com.thoughtworks.gtb.basicquiz.service;

import com.thoughtworks.gtb.basicquiz.domain.Education;
import com.thoughtworks.gtb.basicquiz.exception.UserHasNotEducationException;
import com.thoughtworks.gtb.basicquiz.repo.EducationRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {
    private final EducationRepo educationRepo;

    EducationService(EducationRepo educationRepo) {
        this.educationRepo = educationRepo;
    }

    public List<Education> getEducationByUserId(long id) throws UserHasNotEducationException {
        List<Education> educations = educationRepo.findByUserId(id);
        if(educations.size() == 0) {
            throw new UserHasNotEducationException();
        }
        return educations;
    }

    public List<Education> createEducationByUserId(long id, Education education) {
        education.setUserId(id);
        return educationRepo.save(education);
    }
}
