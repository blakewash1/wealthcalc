package com.blakewashington.wealthcalc.service;

import com.blakewashington.wealthcalc.model.User;
import com.blakewashington.wealthcalc.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(String email, String rawPassword) {
        if (userRepository.findByEmail(email) != null) {
            throw new RuntimeException("User with this email already exists");
        }

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(rawPassword));
        user.setAuthType("local");
        user.setCreatedAt(Instant.now());

        userRepository.save(user);
        return user;
    }

    public User authenticate(String email, String rawPassword) {
        User user = userRepository.findByEmail(email);
        if (user == null || "local".equals(user.getAuthType())) {
            throw new RuntimeException("User not found or wrong auth type");
        }

        if (!passwordEncoder.matches(rawPassword, user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        return user;
    }
}
