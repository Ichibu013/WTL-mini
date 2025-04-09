package com.miniproject.wtlmini.repository;

import com.miniproject.wtlmini.entity.Cart;
import com.miniproject.wtlmini.entity.User;
import org.springframework.data.domain.Limit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
    Cart findByUserIDAndStatus(User userID, String status);
}
