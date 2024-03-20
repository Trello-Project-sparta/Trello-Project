package com.example.trello.comment.entitiy;

import com.example.trello.board.Board;
import com.example.trello.card.Card;
import com.example.trello.comment.dto.CommentRequestDto;
import com.example.trello.common.dto.Timestamped;
import com.example.trello.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

@Entity
@Table(name = "comment")
public class Comment extends Timestamped {

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "cardId")
    private Card card;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String edit;

    public Comment(String contents, User user, Card card) {
        this.contents = contents;
        this.user = user;
        this.card = card;
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.contents = commentRequestDto.getComment();
    }

}
