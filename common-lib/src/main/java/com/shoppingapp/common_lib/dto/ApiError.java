package com.shoppingapp.common_lib.dto;

import org.springframework.http.HttpStatus;

public record ApiError(HttpStatus httpStatusCode, String message, int applicationErrorCode) {
}
