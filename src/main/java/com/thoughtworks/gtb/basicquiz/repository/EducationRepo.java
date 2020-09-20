package com.thoughtworks.gtb.basicquiz.repository;

import com.thoughtworks.gtb.basicquiz.domain.Education;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Component
public class EducationRepo {
    static HashMap<Long, Education> educations = new HashMap<Long, Education>() {{
        put((long) 1, Education.builder().id(1).userId(1).year(1997)
                .title("First level graduation in Graphic Design")
                .description("Aspernatur, mollitia, quos maxime eius suscipit sed beatae ducimus quaerat quibusdam perferendis? Iusto, quibusdam asperiores unde repellat.")
                .build());
        put((long) 2, Education.builder().id(2).userId(1).year(1997)
                .title("Secondary school specializing in artistic")
                .description("Eos, explicabo, nam, tenetur et ab eius deserunt aspernatur ipsum ducimus quibusdam quis voluptatibus.")
                .build());
    }};
    static AtomicLong generateId = new AtomicLong(2);
    public List<Education> findByUserId(long userId) {
        return educations.values().stream().filter(ele -> ele.getUserId() == userId).collect(Collectors.toList());
    }

    public List<Education> save(Education education) {
        long id = generateId.incrementAndGet();
        education.setId(id);
        educations.put(id, education);
        return findByUserId(education.getUserId());
    }
}