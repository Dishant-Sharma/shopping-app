package com.shoppingapp.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class RegistrationResponse {
    private UUID id;
    private String username;
    private String email;
}
