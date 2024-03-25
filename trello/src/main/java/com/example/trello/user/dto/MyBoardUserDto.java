package com.example.trello.user.dto;

import com.example.trello.userBoard.entity.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyBoardUserDto {

	private String username;

	private Long boardId;

	private UserRoleEnum role;
}
