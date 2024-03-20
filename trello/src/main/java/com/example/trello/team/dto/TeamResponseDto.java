package com.example.trello.team.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeamResponseDto {

    private List<String> emailList;
    private String boardTitle;
}
