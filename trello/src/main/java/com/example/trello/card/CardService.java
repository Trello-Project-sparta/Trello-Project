package com.example.trello.card;


import com.example.trello.columnList.ColumnListRepository;
import com.example.trello.columnList.entity.ColumnList;
import com.example.trello.global.exception.NotFoundUserException;
import com.example.trello.user.User;
import com.example.trello.user.UserRepository;
import com.example.trello.userBoard.repository.UserBoardRepository;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CardService {

  private final CardRepository cardRepository;

  private final UserBoardRepository userBoardRepository;

  private final ColumnListRepository columnListRepository;

  private final UserRepository userRepository;

  public CardResponseDto createCard(Long boardId, Long columnId, User creator,
      CardRequestDto cardRequestDto) {
    if (validationUser(boardId, creator.getUserId())) {
      ColumnList column = columnListRepository.findById(columnId).orElseThrow();
      Card card = new Card(cardRequestDto, creator, column);
      cardRepository.save(card);
      return new CardResponseDto(card);
    } else {
      throw new NotFoundUserException();
    }

  }

  public CardResponseDto updateCard(User user, Long boardId, Long cardId,
      CardUpdateDto cardUpdateDto) {
    if (validationUser(boardId, user.getUserId())) {
      Card card = cardRepository.findById(cardId).orElseThrow();
      card.updateCard(cardUpdateDto);
      return new CardResponseDto(card);
    } else {
      throw new NotFoundUserException();
    }
  }

  public CardResponseDto updateDeadline(User user, Long boardId, Long cardId,
      LocalDateTime deadline) {
    if (validationUser(boardId, user.getUserId())) {
      Card card = cardRepository.findById(cardId).orElseThrow();
      card.updateDeadline(deadline);
      return new CardResponseDto(card);
    }
    else {throw new NotFoundUserException();}

  }

  public CardResponseDto updateColumn(User user, Long boardId, Long columnId, Long cardId) {
    if (validationUser(boardId, user.getUserId())) {
      Card card = cardRepository.findById(cardId).orElseThrow();
      ColumnList column = columnListRepository.findById(columnId).orElseThrow();
      card.updateColumn(column);
      return new CardResponseDto(card);
    }
    else {throw new NotFoundUserException();}

  }

  public WorkerResponseDto updateWorker(User user, Long boardId, Long workerId, Long cardId) {
    if (validationUser(boardId, user.getUserId()) && validationUser(boardId, workerId)) {
      Card card = cardRepository.findById(cardId).orElseThrow();
      User worker = userRepository.findById(workerId).orElseThrow();
      card.updateWorker(worker);
      return new WorkerResponseDto(worker.getUsername());
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
//    Board board = boardRepository.findById(boardId).get();
//    List<UserBoard> userBoards = userBoardRepository.findAllByBoardBoardId(boardId);
//    for (UserBoard userBoard : userBoards) {
//      if (userBoard.getUser().getUserId().equals(userId)) {
//        return true; // 로그인 한 사용자가 보드에 속해 있음
//      }
//    }
//    return false; // 로그인 한 사용자가 보드에 속해 있지 않음
    return userBoardRepository.existsByBoardBoardIdAndUserUserId(boardId, userId);
  }


}

