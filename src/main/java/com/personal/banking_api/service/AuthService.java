package com.personal.banking_api.service;

import com.personal.banking_api.model.AuthRequest;
import com.personal.banking_api.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    AuthRepository authRepository;

    public boolean checkCredentials(String username, String pin) {
        return authRepository.existsByUsernameAndPin(username, pin);
    }

    public void setCredentials(AuthRequest credentials) {
        authRepository.save(credentials);
    }
}
