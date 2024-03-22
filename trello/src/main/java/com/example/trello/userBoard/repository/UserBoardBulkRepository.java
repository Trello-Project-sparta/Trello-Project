package com.example.trello.userBoard.repository;

import com.example.trello.userBoard.entity.UserBoard;
import java.sql.PreparedStatement;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserBoardBulkRepository {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void saveBulk(List<UserBoard> userBoardList) {
        String sql = "INSERT INTO user_board (user_id, board_id, role)" +
            "VALUES (?, ?, ?)";

        jdbcTemplate.batchUpdate(sql,
            userBoardList,
            userBoardList.size(),
            (PreparedStatement ps, UserBoard userBoard) -> {
                ps.setLong(1, userBoard.getUser().getUserId());
                ps.setLong(2, userBoard.getBoard().getBoardId());
                ps.setString(3, "GUEST");
            });
    }
}
