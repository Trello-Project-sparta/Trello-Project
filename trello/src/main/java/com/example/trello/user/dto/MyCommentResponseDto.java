package com.example.trello.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyCommentResponseDto {
	private String username;
	private Long cardId;
	private String content;
}
