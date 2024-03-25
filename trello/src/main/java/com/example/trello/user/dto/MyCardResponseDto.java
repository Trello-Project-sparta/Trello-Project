package com.example.trello.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyCardResponseDto {
	private String username;
	private Long boardId;
	private String cardname;
}
