package com.shoppingapp.common_lib.exception;

import com.shoppingapp.common_lib.dto.ApiError;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException{
    private final HttpStatus httpStatusCode;
    private final int applicationErrorCode;


//    public BaseException(ApiError apiError) {
//        super(apiError.message());
//        this.httpStatusCode = apiError.httpStatusCode();
//        this.applicationErrorCode = apiError.applicationErrorCode();
//    }

    public BaseException(ApiError apiError){
        this(apiError, apiError.message());
    }

    public BaseException(ApiError apiError, String message) {
        super(message);
        this.httpStatusCode = apiError.httpStatusCode();
        this.applicationErrorCode = apiError.applicationErrorCode();
    }

}
