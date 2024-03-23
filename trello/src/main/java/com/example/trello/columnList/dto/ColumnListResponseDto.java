package com.example.trello.columnList.dto;

import lombok.Getter;

@Getter
public class ColumnListResponseDto {

    private String title;
    private Integer sequence;
    private String boardTitle;

    public ColumnListResponseDto(String title, Integer sequence, String boardTitle) {
        this.title = title;
        this.sequence = sequence;
        this.boardTitle = boardTitle;
    }
}
