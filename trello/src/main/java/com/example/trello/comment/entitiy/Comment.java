package com.example.trello.comment.entitiy;

import com.example.trello.card.Card;
import com.example.trello.comment.common.BaseEntity;
import com.example.trello.user.User;
import com.example.trello.userBoard.UserBoard;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "card_id", nullable = false)
  private Card card;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private UserBoard userBoard;

  @Builder
  public Comment(String content, Card card, UserBoard userBoard) {
    this.content = content;
    this.card = card;
    this.userBoard = userBoard;
  }

  public void updateComment(String content) {
    this.content = content;
  }
}
