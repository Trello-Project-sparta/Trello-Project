package com.example.trello.card;

import com.example.trello.security.UserDetailsImpl;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class CardController {

  private final CardService cardService;


  @PostMapping("/boards/{boardId}/columns/{columnId}/cards/create")
  public ResponseEntity<CardResponseDto> createCard(@PathVariable Long boardId,
      @PathVariable Long columnId,
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestBody CardRequestDto cardRequestDto) {
    CardResponseDto cardResponseDto = cardService.createCard(boardId, columnId,
        userDetails.getUser(), cardRequestDto);
    return ResponseEntity.ok().body(cardResponseDto);
  }

  @PutMapping("/boards/{boardId}/columns/{columnId}/cards/{cardId}/update")
  public ResponseEntity<CardResponseDto> updateCard(@PathVariable Long boardId,
      @PathVariable Long columnId,
      @PathVariable Long cardId,
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestBody CardUpdateDto cardUpdateDto) {
    CardResponseDto cardResponseDto = cardService.updateCard(userDetails.getUser(), boardId,
        cardId, cardUpdateDto);
    return ResponseEntity.ok().body(cardResponseDto);
  }

  @PutMapping("/boards/{boardId}/columns/{columnId}/cards/{cardId}/deadline/{deadline}")
  public ResponseEntity<CardResponseDto> updateDeadline(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @PathVariable Long boardId,
      @PathVariable Long columnId,
      @PathVariable Long cardId,
      @PathVariable LocalDateTime deadline) {
    CardResponseDto cardResponseDto = cardService.updateDeadline(userDetails.getUser(), boardId,
        cardId, deadline);
    return ResponseEntity.ok().body(cardResponseDto);
  }

  @PutMapping("/boards/{boardId}/cards/{cardId}/worker/{workerId}/updateworker")
  public ResponseEntity<WorkerResponseDto> updateWorker(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @PathVariable Long boardId,
      @PathVariable Long workerId,
      @PathVariable Long cardId) {
    WorkerResponseDto workerResponseDto = cardService.updateWorker(userDetails.getUser(), boardId,
        workerId, cardId);
    return ResponseEntity.ok().body(workerResponseDto);
  }

  @PutMapping("/boards/{boardId}/columns/{columnId}/cards/{cardId}/updatecolumn")
  public ResponseEntity<CardResponseDto> updateColmn(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @PathVariable Long boardId,
      @PathVariable Long columnId,
      @PathVariable Long cardId) {
    CardResponseDto cardResponseDto = cardService.updateColumn(userDetails.getUser(), boardId,
        columnId, cardId);
    return ResponseEntity.ok().body(cardResponseDto);
  }

  @DeleteMapping("/boards/{boardId}/columns/{columnId}/cards/{cardId}/delete")
  public ResponseEntity<Void> deleteCard(@AuthenticationPrincipal UserDetailsImpl userDetails,
      @PathVariable Long boardId, @PathVariable Long cardId) {
    cardService.deleteCard(boardId, cardId, userDetails.getUser());
    return ResponseEntity.ok().build();

  }

}
