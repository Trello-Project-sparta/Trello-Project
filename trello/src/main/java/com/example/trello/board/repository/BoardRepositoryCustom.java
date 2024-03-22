package com.example.trello.board.repository;

import com.example.trello.board.entity.Board;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepositoryCustom {

    List<Board> searchByAny(String search);

    List<Board> searchByBoardIdIn(List<Long> boardIdList, String search);


}
