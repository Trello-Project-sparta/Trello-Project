package com.example.trello.columnList.repository;

import com.example.trello.columnList.entity.ColumnList;
import com.example.trello.columnList.entity.QColumnList;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ColumnListRepositoryImpl implements ColumnListRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    QColumnList columnList = QColumnList.columnList;

    @Override
    public List<ColumnList> searchAllMoreThanSearchAndLessThanSequence(Integer search,
        Integer sequence) {

        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(columnList.sequence.eq(search));
        predicate.or(columnList.sequence.gt(search));
        predicate.and(columnList.sequence.lt(sequence));

        return jpaQueryFactory.selectFrom(columnList)
            .where(predicate)
            .fetch();
    }

    @Override
    public List<ColumnList> searchAllMoreThanSearch(Integer sequence) {
        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(columnList.sequence.gt(sequence));

        return jpaQueryFactory.selectFrom(columnList)
            .where(predicate)
            .fetch();
    }


}
