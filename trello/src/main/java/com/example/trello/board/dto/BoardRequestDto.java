package com.example.trello.board.dto;


import lombok.Getter;

@Getter
public class BoardRequestDto {

    private String title = "board";
    private String background_color = "black";
    private String description = "";
}
