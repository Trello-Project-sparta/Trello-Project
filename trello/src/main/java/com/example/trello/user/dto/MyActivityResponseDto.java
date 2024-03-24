package com.example.trello.user.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyActivityResponseDto {
	private String username;
	private String activity;
}
