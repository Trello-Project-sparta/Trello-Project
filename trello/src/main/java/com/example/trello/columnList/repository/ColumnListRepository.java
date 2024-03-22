package com.example.trello.columnList.repository;

import com.example.trello.columnList.entity.ColumnList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnListRepository extends JpaRepository<ColumnList, Long>,
    ColumnListRepositoryCustom {


    List<ColumnList> findAllByBoardBoardId(Long boardId);

    List<ColumnList> findAllByBoardBoardIdOrderBySequenceDesc(Long boardId);

    Optional<ColumnList> findByBoardBoardIdAndSequence(Long boardId, Integer sequence);
}
