package com.example.trello.global.exception;

public class NotFoundTeamException extends RuntimeException {

    public NotFoundTeamException() {
        super("존재하지 않는 팀입니다.");
    }
}
