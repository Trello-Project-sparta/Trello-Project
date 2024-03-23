package com.example.trello.board.repository;

import com.example.trello.board.entity.Board;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {

    List<Board> findAllByBoardIdIn(List<Long> boardIdList);

}
