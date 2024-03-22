package com.example.trello.user.dto;

import com.example.trello.userBoard.entity.UserRoleEnum;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyBoardUserResponseDto {

	private String username;
	private Long boardId;
	private UserRoleEnum role;
}
