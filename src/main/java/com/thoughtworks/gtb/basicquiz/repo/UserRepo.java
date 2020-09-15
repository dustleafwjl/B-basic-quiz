package com.thoughtworks.gtb.basicquiz.repo;

import com.thoughtworks.gtb.basicquiz.domain.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class UserRepo {
    static HashMap<Long, User> users = new HashMap<Long, User>() {{
        put((long) 1, User.builder().id(1).name("KAMIL").age(24).avatar("https://inews.gtimg.com/newsapp_match/0/3581582328/0").description("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repellendus, non, dolorem, cumque distinctio magni quam expedita velit laborum sunt amet facere tempora ut fuga aliquam ad asperiores voluptatem dolorum! Quasi.").build());
    }};
    static long generateId = 1;
    public User findById(long id) {
        return users.get(id);
    }

    public User save(User user) {
        generateId ++;
        users.put(generateId, User.builder()
                .id(generateId).name(user.getName())
                .age(user.getAge()).avatar(user.getAvatar())
                .description(user.getDescription()).build());
        return users.get(generateId);
    }
}
