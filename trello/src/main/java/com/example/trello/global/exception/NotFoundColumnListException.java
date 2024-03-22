package com.example.trello.global.exception;

public class NotFoundColumnListException extends RuntimeException {

    public NotFoundColumnListException() {
        super("존재하지 않는 컬럼입니다.");
    }
}
