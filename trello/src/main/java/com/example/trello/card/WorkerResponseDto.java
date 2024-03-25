package com.example.trello.card;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WorkerResponseDto {

  private List<String> workernames;

  public WorkerResponseDto(Card card) {

    this.workernames = card.getWorkers().stream()
        .map(userBoard -> userBoard.getUser().getUsername())
        .collect(Collectors.toList());
  }

}
