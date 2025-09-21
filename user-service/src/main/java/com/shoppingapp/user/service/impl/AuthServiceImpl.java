package com.shoppingapp.user.service.impl;

import com.shoppingapp.user.dto.LoginRequest;
import com.shoppingapp.user.dto.LoginResponse;
import com.shoppingapp.user.dto.RegistrationRequest;
import com.shoppingapp.user.dto.RegistrationResponse;
import com.shoppingapp.user.entity.AppUser;
import com.shoppingapp.user.enums.Role;
import com.shoppingapp.user.enums.Status;
import com.shoppingapp.user.repository.AuthRepository;
import com.shoppingapp.user.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {


    private final AuthRepository authRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthRepository authRepository,
                           PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegistrationResponse registerUser(RegistrationRequest request) {
        // check uniqueness of email/username
        if (authRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if (authRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        // hash password
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // build user
        AppUser appUser = AppUser.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(encodedPassword)
                .phone(request.getPhone())
                .role(Role.CUSTOMER)
                .status(Status.INACTIVE)
                .createdAt(LocalDateTime.now())
                .build();

        // save
        AppUser savedAppUser = authRepository.save(appUser);



        // response
        return new RegistrationResponse(savedAppUser.getId(),
                savedAppUser.getUsername(),
                savedAppUser.getEmail());
    }

    @Override
    public LoginResponse loginUser(LoginRequest request) {
        // find user by username OR email
        AppUser appUser = authRepository.findByUsername(request.getUsernameOrEmail())
                .or(() -> authRepository.findByEmail(request.getUsernameOrEmail()))
                .orElseThrow(() -> new RuntimeException("Invalid username/email or password"));

        // validate password
        if (!passwordEncoder.matches(request.getPassword(), appUser.getPassword())) {
            throw new RuntimeException("Invalid username/email or password");
        }

        return new LoginResponse("Login successful", appUser.getUsername(), appUser.getEmail());
    }

    @Override
    public void logoutUser(UUID userId) {
        // For now, no actual token/session handling.
        // Just simulate logout.
        if (!authRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        System.out.println("User with ID " + userId + " has logged out.");
    }

}


