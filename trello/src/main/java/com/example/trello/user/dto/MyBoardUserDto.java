package com.example.trello.user.dto;

import com.example.trello.userBoard.entity.UserRoleEnum;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@NoArgsConstructor
public class MyBoardUserDto {

	private String username;

	private Long boardId;

	private UserRoleEnum role;

	public MyBoardUserDto(String username, Long boardId, UserRoleEnum role) {
		this.username = username;
		this.boardId = boardId;
		this.role = role;
	}
}
