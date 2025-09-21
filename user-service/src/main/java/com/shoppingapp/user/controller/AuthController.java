package com.shoppingapp.user.controller;


import com.shoppingapp.user.dto.*;
import com.shoppingapp.user.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/v1/register")
    public ResponseEntity<RegistrationResponse> register(@RequestBody RegistrationRequest request) {
        log.info("Register API called for username={}, email={}", request.getUsername(), request.getEmail());
        RegistrationResponse response = authService.registerUser(request);
        log.debug("Registration completed for userId={}", response.getUsername());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/v1/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        log.info("Login attempt for username/email={}", request.getUsernameOrEmail());
        LoginResponse response = authService.loginUser(request);
        log.info("Login success for userId={}", response.getUsername());
        return ResponseEntity.ok(response);
    }


    @PostMapping("/v1/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequest request) {
        log.info("Logout request received for userId={}", request.getUserId());
        authService.logoutUser(request.getUserId());
        log.info("Logout completed for userId={}", request.getUserId());
        return ResponseEntity.ok().build();
    }

}