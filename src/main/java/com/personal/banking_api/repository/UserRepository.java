package com.personal.banking_api.repository;

import com.personal.banking_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserID(String userId);
}
