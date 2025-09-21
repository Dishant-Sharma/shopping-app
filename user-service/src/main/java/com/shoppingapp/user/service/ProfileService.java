package com.shoppingapp.user.service;

import com.shoppingapp.user.dto.ChangePasswordRequest;
import com.shoppingapp.user.dto.UpdateProfileRequest;
import com.shoppingapp.user.dto.UserProfileResponse;

import java.util.UUID;

public interface ProfileService {
    UserProfileResponse getProfile(UUID userId);
    UserProfileResponse updateProfile(UUID userId, UpdateProfileRequest request);
    void changePassword(UUID userId, ChangePasswordRequest request);
}
