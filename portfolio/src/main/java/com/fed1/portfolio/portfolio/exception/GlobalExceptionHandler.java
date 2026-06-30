package com.fed1.portfolio.portfolio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidJson(
            HttpMessageNotReadableException ex) {

        ErrorResponse error =
                new ErrorResponse("Invalid JSON request", 400);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Null Pointer
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNull(
            NullPointerException ex) {

        ErrorResponse error =
                new ErrorResponse("Null value found", 400);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Any Other Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
            Exception ex) {

        ErrorResponse error =
                new ErrorResponse(ex.getMessage(), 500);

        return new ResponseEntity<>(error,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
