package com.example.trello.team.repository;


import com.example.trello.board.entity.Board;
import com.example.trello.team.entity.Team;
import com.example.trello.user.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    boolean existsByUserInAndBoardBoardId(List<User> user, Long boardId);

    Optional<Team> findByUserAndBoard(User user, Board board);
    
    List<Team> findAllByBoardBoardId(Long boardId);

    List<Team> findAllByUserInAndBoardBoardId(List<User> userList, Long boardId);
}
