package com.example.trello.userBoard.repository;


import com.example.trello.user.User;
import com.example.trello.userBoard.entity.UserBoard;
import com.example.trello.userBoard.entity.UserRoleEnum;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBoardRepository extends JpaRepository<UserBoard, Long> {

    boolean existsByUserInAndBoardBoardId(List<User> user, Long boardId);

    Optional<UserBoard> findByBoardBoardIdAndUserUserId(Long boardId, Long userId);

    List<UserBoard> findAllByBoardBoardId(Long boardId);

    List<UserBoard> findAllByUserInAndBoardBoardId(List<User> user, Long boardId);

    List<UserBoard> findAllByUserUserIdAndRole(Long userId, UserRoleEnum role);
}
