package com.example.trello.userBoard.repository;


import com.example.trello.board.entity.Board;
import com.example.trello.user.entity.User;
import com.example.trello.userBoard.entity.UserBoard;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBoardRepository extends JpaRepository<UserBoard, Long> {

    boolean existsByUserInAndBoardBoardId(List<User> user, Long boardId);

    Optional<UserBoard> findByUserAndBoard(User user, Board board);

    List<UserBoard> findAllByBoardBoardId(Long boardId);

    List<UserBoard> findAllByUserInAndBoardBoardId(List<User> userList, Long boardId);
}
