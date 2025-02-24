package com.miniproject.wtlmini.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.miniproject.wtlmini.entity.User;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unused")
public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);

    User findByEmail(String email);
}
