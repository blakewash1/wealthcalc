package com.blakewashington.wealthcalc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.util.Map;


@RestController
public class TestController {

    @GetMapping("/test")
    public String getTest() {
        return "we're in";
    }

    @GetMapping("/api/test")
    public String getAuthenticatedTest() {
        return "we're so in";
    }

    @GetMapping("/api/google/me")
    public ResponseEntity<?> getUserInfo(OAuth2AuthenticationToken auth) {
        String userId = auth.getPrincipal().getAttribute("sub");
        String email = auth.getPrincipal().getAttribute("email");
        String name  = auth.getPrincipal().getAttribute("name");

        return ResponseEntity.ok(Map.of("userId", userId, "email", email));
    }
}
