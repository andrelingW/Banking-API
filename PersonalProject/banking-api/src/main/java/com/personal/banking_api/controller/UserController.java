package com.personal.banking_api.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.personal.banking_api.model.User;
import com.personal.banking_api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody JsonNode request) {
        try {
            User user = userService.findUserByUserId(request.get("userId").asText());
            double amount = Double.parseDouble(request.get("amount").asText());

            if(user != null) {
                user.setBalance(user.getBalance() - amount);
                userService.saveUser(user);

                return ResponseEntity.status(HttpStatus.OK).body("withdraw Successful");
            } else {
                throw new Exception("withdraw failed");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid data");
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody JsonNode request) {
        try {
            User user = userService.findUserByUserId(request.get("userId").asText());
            double amount = Double.parseDouble(request.get("amount").asText());

            if(user != null) {
                user.setBalance(user.getBalance() + amount);
                userService.saveUser(user);

                return ResponseEntity.status(HttpStatus.OK).body("Deposit Successful");
            } else {
                throw new Exception("Deposit failed");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid data");
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody JsonNode request) {
        try {
            User user = userService.findUserByUserId(request.get("userId").asText());
            User target = userService.findUserByUserId(request.get("targetUserId").asText());
            double amount = Double.parseDouble(request.get("amount").asText());

            if(user != null && target != null && amount > 0.0 && user.getBalance() >= amount) {
                user.setBalance(user.getBalance() - amount);
                target.setBalance(target.getBalance() + amount);
                userService.saveUser(user);
                userService.saveUser(target);

                return ResponseEntity.status(HttpStatus.CREATED).body("User transferred successfully");
            } else {
                throw new Exception("Transfer failed");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid data");
    }

    @GetMapping("/balance/{id}")
    public ResponseEntity<?> checkBalance(@PathVariable("id") String userID) {
        try {
            Double amount = userService.checkBalance(userID);
            if(!amount.isNaN()){
                return ResponseEntity.ok(amount);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid userID");
    }
}
