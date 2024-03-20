package com.example.trello.userBoard.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserBoardRequestDto {

    private List<String> emailList;

}
