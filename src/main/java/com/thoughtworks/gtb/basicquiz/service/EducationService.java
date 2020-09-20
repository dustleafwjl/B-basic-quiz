package com.thoughtworks.gtb.basicquiz.service;

import com.thoughtworks.gtb.basicquiz.domain.Education;
import com.thoughtworks.gtb.basicquiz.domain.User;
import com.thoughtworks.gtb.basicquiz.exception.UserHasNotEducationException;
import com.thoughtworks.gtb.basicquiz.exception.UserIsNotFoundException;
import com.thoughtworks.gtb.basicquiz.repository.EducationRepository;
import com.thoughtworks.gtb.basicquiz.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {
    private final EducationRepository educationRepository;
    private final UserRepository userRepository;

    EducationService(EducationRepository educationRepo, UserRepository userRepository) {
        this.educationRepository = educationRepo;
        this.userRepository = userRepository;
    }

    public List<Education> getEducationByUserId(long id) throws UserHasNotEducationException {
        List<Education> educations = educationRepository.findAllByUserId(id);
        if(educations.size() == 0) {
            throw new UserHasNotEducationException();
        }
        return educations;
    }

    public List<Education> createEducationByUserId(long id, Education education) throws UserIsNotFoundException {
        User user = userRepository.findById(id).orElseThrow(UserIsNotFoundException::new);
        education.setUser(user);
        educationRepository.save(education);
        return educationRepository.findAll();
    }
}
