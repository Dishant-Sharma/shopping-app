package com.shoppingapp.common_lib.constants;

import com.shoppingapp.common_lib.dto.ApiError;
import org.springframework.http.HttpStatus;

public final class ErrorCodes {
    private ErrorCodes() {
        // do-nothing
    }

    public static final ApiError UNKNOWN_ERROR = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,
            "Internal Server Error", 1);
    public static final ApiError VALIDATION_ERROR = new ApiError(HttpStatus.BAD_REQUEST,
            "Bad Request", 2);
}
