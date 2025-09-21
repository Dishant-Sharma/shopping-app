package com.shoppingapp.user.repository;

import com.shoppingapp.user.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthRepository  extends JpaRepository<AppUser, UUID> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByUsername(String username);
}