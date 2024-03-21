package com.example.trello.card;

import com.example.trello.board.Board;
import com.example.trello.global.exception.NotFoundUserException;
import com.example.trello.user.User;
import com.example.trello.user.UserRepository;
import com.example.trello.userBoard.UserBoard;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CardService {

  private final CardRepository cardRepository;

  private final BoardRepository boardRepository;

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

  public void deleteCard(Long boardId, Long cardId, User user) {
    if (validationUser(boardId, user.getUserId())){
      Card card = cardRepository.findById(cardId).get();
    }
    else {throw new NotFoundUserException();}

  }


  private boolean validationUser(Long boardId, Long userId) {
    Board board = boardRepository.findById(boardId);
    List<UserBoard> userBoards = userBoardRepository.findAllByBoardId(boardId);
    List<Long> userIds = userBoards.stream().map(UserBoard::getUserId).collect(Collectors.toList());
    (userId.equals(board.getuserId) || userIds.contains(userId)) {
      return true;
    }
    return false;
  }


}

