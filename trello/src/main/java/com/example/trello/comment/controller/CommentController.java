package com.example.trello.comment.controller;

import com.example.trello.columns.CommonResponseDto;
import com.example.trello.comment.dto.CommentRequestDto;
import com.example.trello.comment.dto.CommentResponseDto;
import com.example.trello.comment.service.CommentService;
import com.example.trello.common.dto.ResponseDto;
import com.example.trello.security.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// [GET] /api/boards/{boardsId}/columns/{columnId}/cards/{cardId}/comments
// [GET] /api/comments?cardId={cardId}
// /api/comments?cardId={cardId}
@RequestMapping("/api/boards/{boardId}/columns/{columnId}/cards/{cardId}/comments")
@RequiredArgsConstructor
public class CommentController {

  private final CommentService commentService;

  @GetMapping
  public ResponseEntity<List<CommentResponseDto>> getAllComments(@PathVariable Long cardId) {
    List<CommentResponseDto> comments = commentService.getAllComments();
    return ResponseEntity.ok(comments);
  }

  @PostMapping
  public ResponseEntity<CommonResponseDto> createComment(
      @RequestBody CommentRequestDto commentRequestDto,
      @PathVariable(name = "cardId") Long cardId,
      @PathVariable(name = "boardId") Long boardId,
      @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {

    CommonResponseDto response = commentService.createComment(cardId, boardId, userDetails,
        commentRequestDto);
    return ResponseEntity.status(response.getStatusCode()).body(response);
  }

  @GetMapping("/{commentId}")
  public ResponseEntity<CommentResponseDto> getCommentById(
      @PathVariable(name = "commentId") Long commentId
  ) {
    CommentResponseDto comment = commentService.getCommentById(commentId);
    return ResponseEntity.ok(comment);
  }

  @PatchMapping("/{commentId}")
  public ResponseEntity<CommonResponseDto> updateComment(
      @PathVariable(name = "commentId") Long commentId,
      @RequestBody CommentRequestDto commentRequestDto
  ) {
    CommonResponseDto response = commentService.updateComment(commentId, commentRequestDto);
    return ResponseEntity.status(response.getStatusCode()).body(response);
  }

  @DeleteMapping("/{commentId}")
  public ResponseEntity<CommonResponseDto> deleteComment(
      @PathVariable(name = "commentId") Long commentId
  ) {
    CommonResponseDto response = commentService.deleteComment(commentId);
    return ResponseEntity.status(response.getStatusCode()).body(response);
  }
}
