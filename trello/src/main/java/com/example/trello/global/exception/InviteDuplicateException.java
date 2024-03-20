package com.example.trello.global.exception;

public class InviteDuplicateException extends RuntimeException {

    public InviteDuplicateException(String username) {
        super(username + "님은 이미 초대되어 있습니다.");
    }
}
