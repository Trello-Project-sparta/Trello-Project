package com.example.trello.comment.exception;

public class NotFoundBoardException extends IllegalArgumentException {

    public NotFoundBoardException() {
        super("등록된 보드가 없습니다.");
    }
}
