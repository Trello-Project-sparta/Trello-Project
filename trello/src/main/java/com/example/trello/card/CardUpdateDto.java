package com.example.trello.card;

import com.example.trello.user.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CardUpdateDto {
  @NotBlank
  private String cardname;

  private String description;

  private String color;


}
