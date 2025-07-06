package com.blakewashington.wealthcalc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;


@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping("/")
    public String getCredentialsPath() {
        return "we're in";
    }

    @GetMapping("/api/me")
    public ResponseEntity<?> getUserInfo(OAuth2Authen auth)
}
