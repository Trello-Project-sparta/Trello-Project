package com.example.trello.comment.service;

import com.example.trello.board.Board;
import com.example.trello.comment.dto.CommentRequestDto;
import com.example.trello.comment.dto.CommentResponseDto;
import com.example.trello.comment.exception.dto.UnmodifiableException;
import com.example.trello.comment.repository.CommentRepository;
import com.example.trello.security.UserDetailsImpl;
import com.example.trello.user.User;
import com.example.trello.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final UserRepository userRepository;
  private final BoardRepository boardRepository;
  private final CardRepository cardRepository;
  private final CommentRepository commentRepository;

  public CommentResponseDto createComment(Long boardId, CommentRequestDto commentRequestDto,
      UserDetailsImpl userDetails) {
    User user = userRepository.findById(userDetails.getUser().getUserId())
        .orElseThrow(NotFoundUserException::new);
    Board board = boardRepository.findById(boardId)
        .orElseThrow(NotFoundBoardException::new);
    Comment saveComment = new Comment(commentRequestDto.getComment(), user, board);
    commentRepository.save(saveComment);

    return new CommentResponseDto(saveComment, user.getUsername());

  }

  @Transactional
  public CommentResponseDto updateComment(Long commentId, CommentRequestDto commentRequestDto,
      UserDetailsImpl userDetails) {
    Comment comment = commentRepository.findById(commentId).orElseThrow();

    if (!(comment.getUser().getUserId() == userDetails.getUser().getUserId())) {
      throw new UnmodifiableException();
    }

    comment.update(commentRequestDto);

    return new CommentResponseDto(comment, comment.getUser().getUsername());

  }

  public ExceptionDto deleteComment(Long commentId, UserDetailsImpl userDetails) {
    Comment comment = commentRepository.findById(commentId).orElseThrow();

    if (!(comment.getUser().getMemberId() == userDetails.getUser().getUserId())) {
      throw new UnmodifiableException();
    }

    commentRepository.delete(comment);

    String message = "삭제가 정상적으로 처리되었습니다.";
    return new ExceptionDto(message, 200);
  }
}
