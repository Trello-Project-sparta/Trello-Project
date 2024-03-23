package com.example.trello.global.exception;

public class NotFoundUserException extends RuntimeException {

    public NotFoundUserException() {
        super("해당 유저가 존재하지 않습니다.");
    }
}
