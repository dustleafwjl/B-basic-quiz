package com.thoughtworks.gtb.basicquiz.repository;

import com.thoughtworks.gtb.basicquiz.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
}
