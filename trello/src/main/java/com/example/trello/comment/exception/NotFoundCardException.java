package com.example.trello.comment.exception;

public class NotFoundCardException extends IllegalArgumentException {

    public NotFoundCardException() {super("해당 카드를 찾을 수 없습니다");}
}
