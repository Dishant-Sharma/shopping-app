package com.shoppingapp.user.service.impl;

import com.shoppingapp.user.dto.ChangePasswordRequest;
import com.shoppingapp.user.dto.UpdateProfileRequest;
import com.shoppingapp.user.dto.UserProfileResponse;
import com.shoppingapp.user.entity.AppUser;
import com.shoppingapp.user.repository.AuthRepository;
import com.shoppingapp.user.service.ProfileService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@NoArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    AuthRepository authRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserProfileResponse getProfile(UUID userId) {
        AppUser appUser = authRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserProfileResponse(
                appUser.getId(),
                appUser.getUsername(),
                appUser.getEmail(),
                appUser.getPhone(),
                appUser.getRole(),
                appUser.getStatus()
        );
    }

    @Override
    public UserProfileResponse updateProfile(UUID userId, UpdateProfileRequest request) {
        AppUser appUser = authRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

//        if (request.getPhone() != null)
            appUser.setPhone(request.getPhone());

        AppUser updatedAppUser = authRepository.save(appUser);

        return new UserProfileResponse(
                appUser.getId(),
                appUser.getUsername(),
                appUser.getEmail(),
                appUser.getPhone(),
                appUser.getRole(),
                appUser.getStatus()
        );
    }

    @Override
    public void changePassword(UUID userId, ChangePasswordRequest request) {
        AppUser appUser = authRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getOldPassword(), appUser.getPassword())) {
            throw new RuntimeException("Old password does not match");
        }

        appUser.setPassword(passwordEncoder.encode(request.getNewPassword()));
        authRepository.save(appUser);
    }
}
