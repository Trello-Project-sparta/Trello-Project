package com.example.trello.board.repository;

import com.example.trello.board.entity.Board;
import com.example.trello.board.entity.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    QBoard board = QBoard.board;

    @Override
    public List<Board> searchByAny(String search) {

        BooleanBuilder predicate = new BooleanBuilder();
        predicate.or(board.title.contains(search));
        predicate.or(board.description.contains(search));
        predicate.or(board.background_color.eq(search));

        return jpaQueryFactory.selectFrom(board)
            .where(predicate)
            .fetch();
    }
}
