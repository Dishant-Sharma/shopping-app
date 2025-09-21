package com.shoppingapp.common_lib.exception;

import com.shoppingapp.common_lib.constants.ErrorCodes;
import com.shoppingapp.common_lib.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException e) {
        ErrorResponse errorResponse = new ErrorResponse(e);
        return new ResponseEntity<>(errorResponse, e.getHttpStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCodes.UNKNOWN_ERROR.applicationErrorCode(), e.getMessage());
        return new ResponseEntity<>(errorResponse, ErrorCodes.UNKNOWN_ERROR.httpStatusCode());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {


        Map<String, String> errors = e.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField,
                        fieldError -> fieldError.getDefaultMessage()== null? "": fieldError.getDefaultMessage()));

        ErrorResponse errorResponse = new ErrorResponse(ErrorCodes.VALIDATION_ERROR.applicationErrorCode(), e.getMessage(), errors);
        return new ResponseEntity<>(errorResponse, ErrorCodes.VALIDATION_ERROR.httpStatusCode());
    }
}
