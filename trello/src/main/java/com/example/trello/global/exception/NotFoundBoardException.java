package com.example.trello.global.exception;

public class NotFoundBoardException extends RuntimeException {

    public NotFoundBoardException() {
        super("존재하지 않는 보드입니다.");
    }
}
