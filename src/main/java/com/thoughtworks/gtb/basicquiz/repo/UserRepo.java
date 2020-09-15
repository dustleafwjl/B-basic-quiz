package com.thoughtworks.gtb.basicquiz.repo;

import com.thoughtworks.gtb.basicquiz.domain.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class UserRepo {
    static HashMap<Integer, User> users = new HashMap<Integer, User>() {{
        put(1, User.builder().id(1).name("王江林").age(18).avatar("").description("demo test").build());
    }};
    static int generateId = 2;
    public User findById(Integer id) {
        return users.get(id);
    }
}
