package com.personal.banking_api.controller;

import com.personal.banking_api.model.AuthRequest;
import com.personal.banking_api.model.User;
import com.personal.banking_api.service.AuthService;
import com.personal.banking_api.service.UserService;
import com.personal.banking_api.util.CheckerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    CheckerUtil checkerUtil;

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        try {
            if(checkerUtil.informationCheck(user)){
                userService.createUser(user);
                return ResponseEntity.ok().body("User registered successfully");
            } else {
                throw new Exception("Username or password is incorrect");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Username or password is incorrect");
    }

    @PostMapping("/register/pin")
    public ResponseEntity<?> setPin(@RequestBody AuthRequest authRequest){
        try {
            User user = userService.findUserByUserId(authRequest.getUsername());
            if(user != null && checkerUtil.pinCheck(authRequest.getPin())){
                authService.setCredentials(authRequest);
                return ResponseEntity.ok().body("Pin set successfully");
            } else {
                throw new Exception("Could not set pin");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not set pin");
    }


}
