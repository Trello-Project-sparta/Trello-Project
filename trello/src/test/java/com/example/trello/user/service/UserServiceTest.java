package com.example.trello.user.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.trello.user.dto.InActiveResponseDto;
import com.example.trello.user.dto.MyBoardUserResponseDto;
import com.example.trello.user.dto.MyCardResponseDto;
import com.example.trello.user.dto.MyCommentResponseDto;
import com.example.trello.user.dto.SignupRequestDto;
import com.example.trello.user.entity.User;
import com.example.trello.user.repository.UserRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;

	@Test
	void getMyCardsTest() {
		// given
		SignupRequestDto request = new SignupRequestDto("asdf1","asd@email.com","1234");
		User user = new User(request);
		// when
		List<MyCardResponseDto> response = userService.getMyCards(user);
		// then
		assertNotNull(response);
	}

	@Test
	void getMyCommentsTest() {
		// given
		SignupRequestDto request = new SignupRequestDto("asdf1","asd@email.com","1234");
		User user = new User(request);
		// when
		List<MyCommentResponseDto> response = userService.getMyComments(user);
		// then
		assertNotNull(response);
	}

	@Test
	void getMyBoardUsersTest() {
		// given
		Long boardId = 1L;
		SignupRequestDto request = new SignupRequestDto("beunchoi","asd@email.com","1234");
		User user = new User(request);
		// when
		List<MyBoardUserResponseDto> response = userService.getMyBoardUsers(boardId,user);
		// then
		assertNotNull(response);
	}

	@Test
	void getInActiveUserListTest() {
		// given
		boolean active = false;
		// when
		List<InActiveResponseDto> response = userService.getInActiveUserList(active);
		// then
		assertNotNull(response);
	}
}