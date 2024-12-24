package com.personal.banking_api.controller;

import com.personal.banking_api.model.AuthRequest;
import com.personal.banking_api.model.AuthResponse;
import com.personal.banking_api.service.AuthService;
import com.personal.banking_api.util.CheckerUtil;
import com.personal.banking_api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    AuthService service;

    @Autowired
    CheckerUtil checkerUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        if (service.checkCredentials(authRequest.getUsername(), authRequest.getPin()) && checkerUtil.pinCheck(authRequest.getPin())) {
            String token = JwtUtil.generateToken(authRequest.getUsername());
            return ResponseEntity.ok(new AuthResponse(token));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid credentials");
    }
}
