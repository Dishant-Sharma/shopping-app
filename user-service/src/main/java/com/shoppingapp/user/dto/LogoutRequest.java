package com.shoppingapp.user.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class LogoutRequest {
    private UUID userId;
}

