package com.example.trello.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyCommentDto {
	private String username;
	private Long cardId;
	private String content;
}
