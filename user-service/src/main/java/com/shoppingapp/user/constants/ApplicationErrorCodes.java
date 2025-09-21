package com.shoppingapp.user.constants;

import com.shoppingapp.common_lib.dto.ApiError;
import org.springframework.http.HttpStatus;

public class ApplicationErrorCodes {
    private static final int CODE = 1000;
    public static final ApiError USER_NOT_FOUND = new ApiError(HttpStatus.NOT_FOUND, "User not found", CODE + 1);
    public static final ApiError DUPLICATE_USER_EXIST = new ApiError(HttpStatus.BAD_REQUEST, "User already exist", CODE + 2);
    public static final ApiError INVALID_CREDENTIALS = new ApiError(HttpStatus.BAD_REQUEST, "Invalid Credentials", CODE + 3);
    public static final ApiError INCORRECT_PASSWORD = new ApiError(HttpStatus.BAD_REQUEST, "Old password does not match", CODE + 4);

}
