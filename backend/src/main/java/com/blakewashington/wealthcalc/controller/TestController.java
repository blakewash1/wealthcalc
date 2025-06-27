package com.blakewashington.wealthcalc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/env/credentials-path")
    public ResponseEntity<String> getCredentialsPath() {
        String path = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
        if (path == null || path.isBlank()) {
            return ResponseEntity.status(404).body("GOOGLE_APPLICATION_CREDENTIALS is not set.");
        }
        return ResponseEntity.ok(path);
    }
}
