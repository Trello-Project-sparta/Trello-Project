package com.example.trello.userBoard.entity;

import com.example.trello.board.entity.Board;
import com.example.trello.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor

@Entity
@Table(name = "user_board")
public class UserBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userBoardId;

    @Setter
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public UserBoard(User user, Board board, UserRoleEnum role) {
        this.user = user;
        this.board = board;
        this.role = role;
    }

    public UserBoard(Long userBoardId, User user, Board board, UserRoleEnum role) {
        this.userBoardId = userBoardId;
        this.user = user;
        this.board = board;
        this.role = role;
    }

}
