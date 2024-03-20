package com.example.trello.comment.dto;

import com.example.trello.comment.entitiy.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {

  private Long id;
  private String content;
  private Long cardId;
  private Long userId;
  private String userName;
  private String createdAt;
  private String modifiedAt;


  public static CommentResponseDto fromEntity(Comment comment) {
    return new CommentResponseDto(
        comment.getId(),
        comment.getContent(),
        comment.getCard().getCardId(),
        comment.getUser().getUserId(),
        comment.getUser().getUsername(),
        comment.getCreatedAt().toString(),
        comment.getLastModifiedAt().toString()
    );
  }
}
