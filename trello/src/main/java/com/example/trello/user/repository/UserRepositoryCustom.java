package com.example.trello.user.repository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryCustom {
	List<String> getInActiveUserList(boolean active);
}
