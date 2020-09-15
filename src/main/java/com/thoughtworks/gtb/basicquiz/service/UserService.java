package com.thoughtworks.gtb.basicquiz.service;

import com.thoughtworks.gtb.basicquiz.domain.User;
import com.thoughtworks.gtb.basicquiz.repo.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepo userRepo;

    UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    public User getUserById(long id) {
        return userRepo.findById(id);
    }

    public User createUser(User user) {
        return userRepo.save(user);
    }
}
