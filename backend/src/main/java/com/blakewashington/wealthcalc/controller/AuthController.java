package com.blakewashington.wealthcalc.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.blakewashington.wealthcalc.model.User;
import com.blakewashington.wealthcalc.security.GoogleTokenVerifier;
import com.blakewashington.wealthcalc.security.JwtService;
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
    private final JwtService jwtService;
    private final GoogleTokenVerifier googleTokenVerifier;

    public AuthController(AuthService authService, JwtService jwtService,
                          GoogleTokenVerifier googleTokenVerifier) {
        this.authService = authService;
        this.jwtService = jwtService;
        this.googleTokenVerifier = googleTokenVerifier;
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

        String token = jwtService.generateToken(user.getId(), user.getEmail());

        return ResponseEntity.ok(Map.of(
                "accessToken", token,
                "userId", user.getId(),
                "email", user.getEmail()
        ));
    }

    @PostMapping("/google")
    public ResponseEntity<?> googleLogin(@RequestBody Map<String, String> body) {
        String idToken = body.get("idToken");

        DecodedJWT jwt = googleTokenVerifier.verify(idToken);
        String email = jwt.getClaim("email").asString();
        String userId = jwt.getSubject(); // Google's user ID (sub)

        User user = authService.createOrFetchUser(userId, email);
        String token = jwtService.generateToken(user.getId(), user.getEmail());

        return ResponseEntity.ok(Map.of(
                "accessToken", token,
                "userId", user.getId(),
                "email", user.getEmail()
        ));
    }
}
