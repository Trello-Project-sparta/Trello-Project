package com.example.trello.card;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CardRequestDto {

  @NotBlank
  private String cardname;

  private String description;

  private String color;

  private LocalDateTime deadline;

}
