package com.example.trello.comment.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j(topic = "CustomExceptionHandler")
@RestControllerAdvice
public class CustomExceptionHandler {

    //기본 CustomException
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomExceptionResponse> handleCustomException(CustomException e) {
        return ResponseEntity.status(e.getStatus()).body(
                CustomExceptionResponse.builder()
                        .errorCode(e.getErrorCode())
                        .statusMessage(e.getMessage())
                        .build()
        );
    }
}
