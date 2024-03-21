package com.example.trello.card;

import com.example.trello.columnList.ColumnList;
import com.example.trello.user.User;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CardRequestDto {

  @NotBlank
  private String cardname;

  private String description;

  private String color;

  private User worker;

  @NotBlank
  private ColumnList column;

  private LocalDateTime deadline;

}
