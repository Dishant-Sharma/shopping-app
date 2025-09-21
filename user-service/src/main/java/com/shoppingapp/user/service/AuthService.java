package com.shoppingapp.user.service;

import com.shoppingapp.user.dto.LoginRequest;
import com.shoppingapp.user.dto.LoginResponse;
import com.shoppingapp.user.dto.RegistrationRequest;
import com.shoppingapp.user.dto.RegistrationResponse;

import java.util.UUID;

public interface AuthService {
    RegistrationResponse registerUser(RegistrationRequest request);
    LoginResponse loginUser(LoginRequest request);
    void logoutUser(UUID userId);
}
