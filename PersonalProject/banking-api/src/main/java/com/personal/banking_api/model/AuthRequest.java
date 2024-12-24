package com.personal.banking_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "auth")
public class AuthRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define the generation strategy
    private Long id;

    String username;
    String pin;

    public AuthRequest(String username, String pin) {
        this.username = username;
        this.pin = pin;
    }

    public AuthRequest() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
