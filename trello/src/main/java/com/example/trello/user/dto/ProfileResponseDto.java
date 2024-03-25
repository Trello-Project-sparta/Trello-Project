package com.example.trello.user.dto;

import com.example.trello.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProfileResponseDto {

	private String username;
	private String email;
	private String userInfo;

	public ProfileResponseDto(User findUser) {
		this.email = findUser.getEmail();
		this.username = findUser.getUsername();
		this.userInfo = findUser.getUserInfo();
	}
}
