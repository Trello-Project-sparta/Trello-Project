package com.example.trello.global.exception;

public class InvalidInviteRightException extends RuntimeException {

    public InvalidInviteRightException(String username) {
        super(username + "님은 팀 관리에 대한 권한이 없습니다.");
    }
}
