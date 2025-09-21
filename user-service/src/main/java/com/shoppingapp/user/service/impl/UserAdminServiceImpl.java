package com.shoppingapp.user.service.impl;

import com.shoppingapp.common_lib.exception.BaseException;
import com.shoppingapp.user.constants.ApplicationErrorCodes;
import com.shoppingapp.user.entity.AppUser;
import com.shoppingapp.user.enums.Role;
import com.shoppingapp.user.enums.Status;
import com.shoppingapp.user.repository.AuthRepository;
import com.shoppingapp.user.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAdminServiceImpl implements AdminService {

    private final AuthRepository authRepository;

    @Override
    public List<AppUser> getAllUsers(int page, int size) {
        return authRepository.findAll(PageRequest.of(page, size))
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AppUser getUserById(UUID id) {
        AppUser appUser = authRepository.findById(id)
                .orElseThrow(() ->  new BaseException(ApplicationErrorCodes.USER_NOT_FOUND));
        return mapToDto(appUser);
    }

    @Override
    public AppUser updateUserRole(UUID id, String newRole) {
        AppUser appUser = authRepository.findById(id)
                .orElseThrow(() ->  new BaseException(ApplicationErrorCodes.USER_NOT_FOUND));
        appUser.setRole(Role.valueOf(newRole));
        AppUser updated = authRepository.save(appUser);
        return mapToDto(updated);
    }

    @Override
    public void deleteUser(UUID id) {
        AppUser appUser = authRepository.findById(id)
                .orElseThrow(() ->  new BaseException(ApplicationErrorCodes.USER_NOT_FOUND));
        appUser.setStatus(Status.DELETED); // soft delete
        authRepository.save(appUser);
    }

    private AppUser mapToDto(AppUser appUser) {
        return AppUser.builder()
                .id(appUser.getId())
                .username(appUser.getUsername())
                .email(appUser.getEmail())
                .role(appUser.getRole())
                .status(appUser.getStatus())
                .build();
    }
}
