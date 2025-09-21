package com.shoppingapp.user.dto;


import com.shoppingapp.user.enums.Role;
import com.shoppingapp.user.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserProfileResponse {
    private UUID id;
    private String username;
    private String email;
    private String phone;
    private Role role;
    private Status status;
}
