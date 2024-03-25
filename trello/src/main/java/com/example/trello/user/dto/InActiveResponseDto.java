package com.example.trello.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InActiveResponseDto {
	private String username;
	private boolean active;
}
