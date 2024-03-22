package com.example.trello.user.repository;

import com.example.trello.user.dto.MyBoardUserDto;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryCustom {

	List<String> getInActiveUserList(boolean active);

	List<MyBoardUserDto> getMyBoardUsers(Long boardNum);
}
