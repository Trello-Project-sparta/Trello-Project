package com.example.trello.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyCardDto {
	private String username;
	private Long boardId;
	private String cardname;
}
