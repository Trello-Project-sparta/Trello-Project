package com.example.trello.user.repository;

import com.example.trello.user.dto.MyBoardUserDto;
import com.example.trello.user.dto.MyCardDto;
import com.example.trello.user.dto.MyCommentDto;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryCustom {

	List<String> getInActiveUserList(boolean active);

	List<MyBoardUserDto> getMyBoardUsers(Long boardNum);
	List<MyCardDto> getMyCards(String username);
	List<MyCommentDto> getMyComments(String username);

}
