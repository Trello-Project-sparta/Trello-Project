package com.example.trello.global.exception;

public class InvalidUserException extends RuntimeException {

    public InvalidUserException() {
        super("보드 수정/삭제 권한이 없습니다.");
    }
    
}
