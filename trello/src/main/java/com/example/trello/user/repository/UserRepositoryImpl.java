package com.example.trello.user.repository;

import com.example.trello.board.entity.QBoard;
import com.example.trello.card.QCard;
import com.example.trello.comment.QComment;
import com.example.trello.user.dto.MyBoardUserDto;
import com.example.trello.user.dto.MyCardDto;
import com.example.trello.user.dto.MyCommentDto;
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

	@Override
	public List<MyCardDto> getMyCards(String username) {
		QUser user = QUser.user;
		QUserBoard userBoard = QUserBoard.userBoard;
		QCard card = QCard.card;

		return jpaQueryFactory.select
			(Projections.bean(MyCardDto.class, user.username,
				userBoard.board.boardId, card.cardname))
			.from(user)
			.innerJoin(userBoard).on(user.userId.eq(userBoard.user.userId)).fetchJoin()
			.innerJoin(card).on(userBoard.userBoardId.eq(card.userBoard.userBoardId)).fetchJoin()
			.where(user.username.eq(username))
			.fetch();
	}

	@Override
	public List<MyCommentDto> getMyComments(String username) {
		QUser user = QUser.user;
		QUserBoard userBoard = QUserBoard.userBoard;
		QComment comment = QComment.comment;

		return jpaQueryFactory.select
			(Projections.bean(MyCommentDto.class, user.username,
				comment.card.cardId, comment.content))
			.from(user)
			.innerJoin(userBoard).on(user.userId.eq(userBoard.user.userId)).fetchJoin()
			.innerJoin(comment).on(userBoard.userBoardId.eq(comment.userBoard.userBoardId)).fetchJoin()
			.where(user.username.eq(username))
			.fetch();
	}
}
