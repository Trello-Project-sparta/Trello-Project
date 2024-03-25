package com.example.trello.comment.common.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CustomExceptionResponse {
    private final CustomErrorCode errorCode;
    private final String statusMessage;

    @Builder
    public CustomExceptionResponse(CustomErrorCode errorCode, String statusMessage) {
        this.errorCode = errorCode;
        this.statusMessage = statusMessage;
    }
}