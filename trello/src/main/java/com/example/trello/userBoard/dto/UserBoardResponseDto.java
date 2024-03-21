package com.example.trello.userBoard.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserBoardResponseDto {

    private List<String> emailList;
    private String boardTitle;
}
