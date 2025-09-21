package com.shoppingapp.user.controller;

import com.shoppingapp.user.entity.AppUser;
import com.shoppingapp.user.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    /**
     * Get all users with pagination (ADMIN only).
     */
    @GetMapping
    public List<AppUser> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return adminService.getAllUsers(page, size);
    }

    /**
     * Get specific user by ID (ADMIN only).
     */
    @GetMapping("/{id}")
    public AppUser getUserById(@PathVariable UUID id) {
        return adminService.getUserById(id);
    }

    /**
     * Update a user’s role (ADMIN only).
     */
    @PatchMapping("/{id}/role")
    public AppUser updateUserRole(
            @PathVariable UUID id,
            @RequestParam String newRole
    ) {
        return adminService.updateUserRole(id, newRole);
    }

    /**
     * Deactivate/Delete a user (ADMIN only).
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID id) {
        adminService.deleteUser(id);
    }
}

