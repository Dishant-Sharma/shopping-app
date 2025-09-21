package com.shoppingapp.user.service;

import com.shoppingapp.user.entity.AppUser;

import java.util.List;
import java.util.UUID;

public interface AdminService {
    List<AppUser> getAllUsers(int page, int size);

    AppUser getUserById(UUID id);

    AppUser updateUserRole(UUID id, String newRole);

    void deleteUser(UUID id);
}

