package com.thoughtworks.gtb.basicquiz.service;

import com.thoughtworks.gtb.basicquiz.domain.Education;
import com.thoughtworks.gtb.basicquiz.exception.UserHasNotEducationException;
import com.thoughtworks.gtb.basicquiz.repository.EducationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {
    private final EducationRepository educationRepository;

    EducationService(EducationRepository educationRepo) {
        this.educationRepository = educationRepo;
    }

    public List<Education> getEducationByUserId(long id) throws UserHasNotEducationException {
        List<Education> educations = educationRepository.findAllByUserId(id);
        if(educations.size() == 0) {
            throw new UserHasNotEducationException();
        }
        return educations;
    }

    public List<Education> createEducationByUserId(long id, Education education) {
        education.setUserId(id);
        educationRepository.save(education);
        return educationRepository.findAll();
    }
}
