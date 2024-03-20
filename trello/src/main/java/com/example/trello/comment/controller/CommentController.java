package com.example.trello.comment.controller;

import com.example.trello.comment.dto.CommentRequestDto;
import com.example.trello.comment.dto.CommentResponseDto;
import com.example.trello.comment.service.CommentService;
import com.example.trello.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/boards/{boardId}/columns/{coIumnId}/cards/{cardId}/comments")
@RequiredArgsConstructor
public class CommentController {

  private final CommentService commentService;

  @PostMapping("")
  public CommentResponseDto createComment(
      @PathVariable Long cardId,
      @Valid @RequestBody CommentRequestDto commentRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    return commentService.createComment(cardId, commentRequestDto, userDetails);
  }

  @PatchMapping("/{commentId}")
  public CommentResponseDto updateComment(
      @PathVariable Long commentId,
      @Valid @RequestBody CommentRequestDto commentRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    return commentService.updateComment(commentId, commentRequestDto, userDetails);
  }

  @DeleteMapping("/{commentId}")
  public Exception deleteComment(
      @PathVariable Long commentId,
      @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {

    return commentService.deleteComment(commentId, userDetails);
  }



}
