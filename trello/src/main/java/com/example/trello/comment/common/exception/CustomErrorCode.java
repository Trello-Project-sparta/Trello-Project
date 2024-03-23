package com.example.trello.comment.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {

    //COMMENT
    COMMENT_NOT_FOUND_EXCEPTION("존재하지 않는 댓글입니다."),
    //COLUMN
    COLUMN_NOT_FOUND_EXCEPTION("존재하지 않는 칼럼입니다"),
    //USER
    USER_NOT_FOUND_EXCEPTION("존재하지 않는 유저입니다"),
    //CARD
    CARD_NOT_FOUND_EXCEPTION("존재하지 않는 카드입니다");


    private final String statusMessage;
}
