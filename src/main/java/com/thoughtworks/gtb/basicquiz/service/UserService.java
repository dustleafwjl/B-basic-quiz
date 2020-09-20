package com.thoughtworks.gtb.basicquiz.service;

import com.thoughtworks.gtb.basicquiz.domain.User;
import com.thoughtworks.gtb.basicquiz.exception.UserIsNotFoundException;
import com.thoughtworks.gtb.basicquiz.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(long id) throws Exception{
        User user = userRepository.findById(id).orElseThrow(UserIsNotFoundException::new);
        return user;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}
