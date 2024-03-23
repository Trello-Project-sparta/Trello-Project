package com.example.trello.card;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WorkerResponseDto {
  private String workername;

  public WorkerResponseDto(String workername) {
    this.workername = workername;
  }

}
