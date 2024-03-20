package com.example.trello.comment.dto;

import com.example.trello.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {

  private Long commentId;
  private String comment;

  public CommentResponseDto(Comment comment, String username) {
    this.commentId = comment.getCommentId();
    this.comment = comment.getContents();
  }

}
