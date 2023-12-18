package com.cybersoft.cozastore.exception;

import com.cybersoft.cozastore.payload.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handleException(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseError(
                       500,
                        "Internal server error",
                        new Timestamp(System.currentTimeMillis()),
                        ex.getMessage(),
                        ex.getLocalizedMessage()
                )
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseError> handleUserNotFoundException(Exception ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseError(
                        404,
                        "User not found",
                        new Timestamp(System.currentTimeMillis()),
                        ex.getMessage(),
                        ex.getLocalizedMessage()
                )
        );
    }
}
