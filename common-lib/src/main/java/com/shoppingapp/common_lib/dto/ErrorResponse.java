package com.shoppingapp.common_lib.dto;

import com.shoppingapp.common_lib.exception.BaseException;
import lombok.Getter;

import java.util.Map;

@Getter
public class ErrorResponse {
    private final int errorCode;
    private final String message;
    Map<String, String> fieldErrors;

    public ErrorResponse(BaseException e) {
//        this.errorCode = e.getApplicationErrorCode();
//        this.message = e.getMessage();
        this(e.getApplicationErrorCode(), e.getMessage(), null);
    }

    public ErrorResponse(int errorCode, String message) {
//        this.errorCode = errorCode;
//        this.message = message;
        this(errorCode, message, null);
    }

    public ErrorResponse(int errorCode, String message, Map<String, String> fieldErrors) {
        this.errorCode = errorCode;
        this.message = message;
        this.fieldErrors = fieldErrors;
    }
}
