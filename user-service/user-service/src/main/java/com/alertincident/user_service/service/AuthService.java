package com.alertincident.user_service.service;

import com.alertincident.user_service.model.User;
import com.alertincident.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User authenticate(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password)
               .orElseThrow(() -> new RuntimeException("Authentication failed: Invalid credentials"));
    }

    public User getCurrentUser(String email) {
        return userRepository.findByEmail(email)
               .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }
}