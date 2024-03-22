package com.example.trello.user.repository;

import com.example.trello.board.entity.QBoard;
import com.example.trello.user.dto.MyBoardUserDto;
import com.example.trello.user.entity.QUser;
import com.example.trello.userBoard.entity.QUserBoard;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<String> getInActiveUserList(boolean active) {
		QUser user = QUser.user;

		return jpaQueryFactory.select(user.username)
			.from(user)
			.where(user.active.eq(active))
			.fetch();
	}

	@Override
	public List<MyBoardUserDto> getMyBoardUsers(Long boardNum) {
		QUser user = QUser.user;
		QUserBoard userBoard = QUserBoard.userBoard;
		QBoard board = QBoard.board;

		return jpaQueryFactory.select
				(Projections.bean(MyBoardUserDto.class, user.username, userBoard.board.boardId,
					userBoard.role))
			.from(user)
			.innerJoin(userBoard).on(user.userId.eq(userBoard.user.userId)).fetchJoin()
			.where(board.boardId.eq(boardNum))
			.fetch();
	}
}
