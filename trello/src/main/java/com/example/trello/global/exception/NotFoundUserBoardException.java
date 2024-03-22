package com.example.trello.global.exception;

public class NotFoundUserBoardException extends RuntimeException {

    public NotFoundUserBoardException() {
        super("존재하지 않는 팀입니다.");
    }
}
