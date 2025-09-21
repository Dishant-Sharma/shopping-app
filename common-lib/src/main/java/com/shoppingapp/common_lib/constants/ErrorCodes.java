package com.shoppingapp.common_lib.constants;

import com.shoppingapp.common_lib.dto.ApiError;
import org.springframework.http.HttpStatus;

public class ErrorCodes {
    public static final ApiError UNKNOWN_ERROR = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,
            "Internal Server Error", 1);
}
