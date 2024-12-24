package com.personal.banking_api.service;

import com.personal.banking_api.model.User;
import com.personal.banking_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void createUser(User user) {
        user.setBalance(0.0);
        userRepository.save(user);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User findUserByUserId(String userId) {
        return userRepository.findByUserID(userId);
    }

    public double checkBalance(String userId) {
        return userRepository.findByUserID(userId).getBalance();
    }
}
