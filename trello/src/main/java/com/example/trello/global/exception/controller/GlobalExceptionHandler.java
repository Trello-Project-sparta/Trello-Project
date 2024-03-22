package com.example.trello.global.exception.controller;

import com.example.trello.global.exception.InvalidInviteRightException;
import com.example.trello.global.exception.InvalidUserException;
import com.example.trello.global.exception.NotFoundBoardException;
import com.example.trello.global.exception.NotFoundColumnListException;
import com.example.trello.global.exception.NotFoundUserBoardException;
import com.example.trello.global.exception.NotfoundUserException;
import com.example.trello.global.exception.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidInviteRightException.class)
    public ResponseEntity<ErrorResponseDto> handleInviteDuplicateException(
        InvalidInviteRightException e) {
        return ResponseEntity.badRequest()
            .body(new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler(NotFoundBoardException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundBoardException(NotFoundBoardException e) {
        return ResponseEntity.badRequest()
            .body(new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler(NotFoundUserBoardException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundTeamException(
        NotFoundUserBoardException e) {
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

    @ExceptionHandler(NotFoundColumnListException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundColumnListException(
        NotFoundColumnListException e) {
        return ResponseEntity.badRequest()
            .body(new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }
}