package com.example.trello.card;

import com.example.trello.board.entity.Board;
import com.example.trello.board.repository.BoardRepository;
import com.example.trello.columnList.ColumnList;
import com.example.trello.global.exception.NotFoundUserException;
import com.example.trello.user.User;
import com.example.trello.userBoard.entity.UserBoard;
import com.example.trello.userBoard.repository.UserBoardRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CardService {

  private final CardRepository cardRepository;

  private final BoardRepository boardRepository;

  private final UserBoardRepository userBoardRepository;

  private final ColumnListRepository columnListRepository;

  public CardResponseDto createCard(Long boardId, Long columnId, User creator,
      CardRequestDto cardRequestDto) {
    if (validationUser(boardId, creator.getUserId())) {
      Card card = new Card(cardRequestDto, creator);
      cardRepository.save(card);
      return new CardResponseDto(card);
    } else {
      throw new NotFoundUserException();
    }

  }

  public CardResponseDto updateCard(User user, Long boardId, Long columnId, Long cardId,
      CardUpdateDto cardUpdateDto) {
    if (validationUser(boardId, user.getUserId())) {
      Card card = cardRepository.findById(cardId).get();
      card.updateCard(cardUpdateDto);
      return new CardResponseDto(card);
    } else {
      throw new NotFoundUserException();
    }
  }

  public CardResponseDto updateDeadline(User user, Long boardId, Long columnId, Long cardId,
      LocalDateTime deadline) {
    if (validationUser(boardId, user.getUserId())) {
      Card card = cardRepository.findById(cardId).get();
      card.updateDeadline(deadline);
      return new CardResponseDto(card);
    }
    else {throw new NotFoundUserException();}

  }

  public CardResponseDto updateColumn(User user, Long boardId, Long columnId, Long cardId) {
    if (validationUser(boardId, user.getUserId())) {
      Card card = cardRepository.findById(cardId).get();
      ColumnList column = columnListRepository.findById(columnId).get();
      card.updateColumn(column);
      return new CardResponseDto(card);
    }
    else {throw new NotFoundUserException();}

  }

  public void deleteCard(Long boardId, Long cardId, User user) {
    if (validationUser(boardId, user.getUserId())){
   cardRepository.deleteById(cardId);
    }
    else {throw new NotFoundUserException();}

  }


  private boolean validationUser(Long boardId, Long userId) {
    Board board = boardRepository.findById(boardId).get();
    List<UserBoard> userBoards = userBoardRepository.findAllByBoardBoardId(boardId);
    for (UserBoard userBoard : userBoards) {
      if (userBoard.getUser().getUserId().equals(userId)) {
        return true; // 로그인 한 사용자가 보드에 속해 있음
      }
    }
    return false; // 로그인 한 사용자가 보드에 속해 있지 않음
  }


}

