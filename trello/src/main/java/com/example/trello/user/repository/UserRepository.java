package com.example.trello.user.repository;

import com.example.trello.user.dto.MyActivityInterface;
import com.example.trello.user.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    List<User> findAllByEmailIn(List<String> emailList);

    @Query(nativeQuery = true,
        value = "SELECT u.username, ca.cardname AS activity "
            + "FROM user u "
            + "inner JOIN user_board ub ON u.user_id = ub.user_id "
            + "inner JOIN card ca ON ub.user_board_id = ca.user_board_id "
            + "WHERE u.username = :username "
            + "UNION "
            + "SELECT u.username, c.content AS activity "
            + "FROM user u "
            + "inner JOIN user_board ub ON u.user_id = ub.user_id "
            + "inner JOIN comment c ON ub.user_board_id = c.user_board_id "
            + "WHERE u.username = :username ")
    List<MyActivityInterface> getMyActivities(@Param("username") String username);
}
