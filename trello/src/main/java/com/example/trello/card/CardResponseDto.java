package com.example.trello.card;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardResponseDto {
  private Long cardId;
  private String cardname;
  private String creatorname;
  private String description;
  private String color;
  private LocalDateTime deadline;

  public CardResponseDto(Card card) {
    this.cardId = card.getCardId();
    this.cardname = card.getCardname();
    this.creatorname = card.getUserBoard().getUser().getUsername();
    this.description = card.getDescription();
    this.color = card.getColor();
    this.deadline = card.getDeadline();
  }

}
