package com.example.trello.global.exception;

public class NotFoundUserException extends IllegalArgumentException{
    public NotFoundUserException() {
        super("보드 사용자가 아닙니다");
    }

    public NotFoundUserException(String s) {
        super(s);
    }
}
