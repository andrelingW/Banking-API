package com.personal.banking_api.repository;

import com.personal.banking_api.model.AuthRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<AuthRequest, Long> {
    boolean existsByUsernameAndPin(String username, String pin);
}
