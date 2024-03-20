package com.example.trello.global.exception.controller;

import com.example.trello.global.NotfoundUserException;
import com.example.trello.global.exception.InvalidUserException;
import com.example.trello.global.exception.InviteDuplicateException;
import com.example.trello.global.exception.NotFoundBoardException;
import com.example.trello.global.exception.NotFoundTeamException;
import com.example.trello.global.exception.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InviteDuplicateException.class)
    public ResponseEntity<ErrorResponseDto> handleInviteDuplicateException(
        InviteDuplicateException e) {
        return ResponseEntity.badRequest()
            .body(new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler(NotFoundBoardException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundBoardException(NotFoundBoardException e) {
        return ResponseEntity.badRequest()
            .body(new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler(NotFoundTeamException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundTeamException(NotFoundTeamException e) {
        return ResponseEntity.badRequest()
            .body(new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidUserException(InvalidUserException e) {
        return ResponseEntity.badRequest()
            .body(new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler(NotfoundUserException.class)
    public ResponseEntity<ErrorResponseDto> handleNotfoundUserException(NotfoundUserException e) {
        return ResponseEntity.badRequest()
            .body(new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }
}