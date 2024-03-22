package com.example.trello.user.repository;

import com.example.trello.user.entity.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{

	private final JPAQueryFactory jpaQueryFactory;
	@Override
	public List<String> getInActiveUserList(boolean active) {
		QUser user = QUser.user;
		return jpaQueryFactory.select(user.username)
			.from(user)
			.where(user.active.eq(active))
			.fetch();
	}
}
