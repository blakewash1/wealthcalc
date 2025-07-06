package com.blakewashington.wealthcalc.controller;

import com.blakewashington.wealthcalc.model.User;
import com.blakewashington.wealthcalc.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        String password = requestBody.get("password");
        User user = authService.register(email, password);
        return ResponseEntity.ok(Map.of("userId", user.getId(), "email", user.getEmail()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        String password = requestBody.get("password");
        User user = authService.authenticate(email, password);

        //TODO: generate and return JWT
        return ResponseEntity.ok(Map.of("userId", user.getId(), "email", user.getEmail()));
    }
}
