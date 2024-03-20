package com.example.trello.global.exception;

public class NotfoundUserException extends RuntimeException {

    public NotfoundUserException() {
        super("해당 유저가 존재하지 않습니다.");
    }
}
