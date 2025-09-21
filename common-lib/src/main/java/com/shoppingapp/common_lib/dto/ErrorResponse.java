package com.shoppingapp.common_lib.dto;

import com.shoppingapp.common_lib.exception.BaseException;
import lombok.Getter;

@Getter
public class ErrorResponse {
    private final int errorCode;
    private final String message;

    public ErrorResponse(BaseException e) {
//        this.errorCode = e.getApplicationErrorCode();
//        this.message = e.getMessage();
        this(e.getApplicationErrorCode(), e.getMessage());
    }
    public ErrorResponse(int errorCode, String message){
        this.errorCode =errorCode;
        this.message = message;
    }

}
