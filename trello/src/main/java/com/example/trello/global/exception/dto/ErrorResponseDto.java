package com.example.trello.global.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponseDto {

    /**
     * HTTP 응답 상태 코드
     */
    private int status;
    /**
     * 응답 메시지
     */
    private String message;
}
