package com.example.trello.card;

import com.example.trello.board.Board;
import com.example.trello.user.User;
import com.example.trello.userBoard.UserBoard;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {

  private final CardRepository cardRepository;

  private final UserRepository userRepository;

  private final ColumnListRepositury columnListRepositury;

  public CardResponseDto createCard(Long boardId, Long columnId, User creator, CardRequestDto cardRequestDto) {
    if(validationUser(boardId,creator.getUserId())){
      Card card = new Card(cardRequestDto, creator);
      cardRepository.save(card);

    }


  }


  private boolean validationUser(Long boardId, Long userId) {
    Board board = boradRepository.findById(boardId);
    List<UserBoard> userBoards = userBoardRepository.findByBoardId(boardId);
    List<Long> userIds = userBoards.stream().map(UserBoard::getUserId).collect(Collectors.toList());
    (userId.equals(board.getuserId) || userIds.contains(userId)) {
    return true;}
    return false;
  }


}
