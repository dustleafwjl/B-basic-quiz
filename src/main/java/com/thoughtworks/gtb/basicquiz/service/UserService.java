package com.thoughtworks.gtb.basicquiz.service;

import com.thoughtworks.gtb.basicquiz.domain.User;
import com.thoughtworks.gtb.basicquiz.exception.UserIsNotFoundException;
import com.thoughtworks.gtb.basicquiz.repo.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepo userRepo;

    UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User getUserById(long id) throws Exception{
        // GTB: 这一段写的太低级了，跟大家交流一下，看看怎么改进！（用 Optional）
        User user  = null;
        user = userRepo.findById(id);
        if (user == null) {
            throw new UserIsNotFoundException();
        }
        return user;
    }

    public User createUser(User user) {
        return userRepo.save(user);
    }
}
