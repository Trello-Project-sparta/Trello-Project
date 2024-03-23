package com.example.trello.comment.service;

import com.example.trello.card.Card;
import com.example.trello.card.CardRepository;
import com.example.trello.columns.CommonResponseDto;
import com.example.trello.comment.common.exception.CustomErrorCode;
import com.example.trello.comment.common.exception.CustomException;
import com.example.trello.comment.dto.CommentRequestDto;
import com.example.trello.comment.dto.CommentResponseDto;
import com.example.trello.comment.entity.Comment;
import com.example.trello.comment.repository.CommentRepository;
import com.example.trello.security.UserDetailsImpl;
import com.example.trello.user.User;
import com.example.trello.userBoard.entity.UserBoard;
import com.example.trello.userBoard.repository.UserBoardRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;
  private final CardRepository cardRepository;
  private final UserBoardRepository userBoardRepository;

  @Transactional
  public CommonResponseDto createComment(Long cardId, UserDetailsImpl userDetails,CommentRequestDto commentRequestDto) {
    Card card = cardRepository.findById(cardId)
        .orElseThrow(() -> new CustomException(CustomErrorCode.CARD_NOT_FOUND_EXCEPTION, 404))
        .getCard();

    User user =userDetails.getUser();

    UserBoard userBoard = userBoardRepository.findById(1L).orElseThrow();

    Comment comment = Comment.builder()
        .content(commentRequestDto.getContent())
        .card(card)
        .userBoard(userBoard)
        .build();

    commentRepository.save(comment);
    return new CommonResponseDto("댓글 생성 완료", 200);
  }

  @Transactional
  public List<CommentResponseDto> getAllComments() {
    List<Comment> comments = commentRepository.findAll();
    return comments.stream()
        .map(CommentResponseDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Transactional
  public CommentResponseDto getCommentById(Long id) {
    Comment comment = commentRepository.findById(id)
        .orElseThrow(() -> new CustomException(CustomErrorCode.COMMENT_NOT_FOUND_EXCEPTION, 404));

    return CommentResponseDto.fromEntity(comment);
  }

  @Transactional
  public CommonResponseDto updateComment(Long id, CommentRequestDto commentRequestDto) {
    Comment comment = commentRepository.findById(id)
        .orElseThrow(() -> new CustomException(CustomErrorCode.COMMENT_NOT_FOUND_EXCEPTION, 404));

    comment.updateComment(commentRequestDto.getContent());
    return new CommonResponseDto("댓글 수정 완료", 200);
  }

  @Transactional
  public CommonResponseDto deleteComment(Long id) {
    commentRepository.deleteById(id);
    return new CommonResponseDto("댓글 삭제 완료", 200);
  }
}
