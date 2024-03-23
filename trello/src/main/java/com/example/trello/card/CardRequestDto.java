package com.example.trello.card;

import com.example.trello.columnList.entity.ColumnList;
import com.example.trello.user.entity.User;
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
