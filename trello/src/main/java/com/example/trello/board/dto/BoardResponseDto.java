package com.example.trello.board.dto;

import com.example.trello.board.entity.Board;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class BoardResponseDto {

    private String title;
    private String background_color;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public BoardResponseDto(String title, String background_color, String description,
        LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.title = title;
        this.background_color = background_color;
        this.description = description;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public BoardResponseDto(Board board) {
        this.title = board.getTitle();
        this.background_color = board.getBackground_color();
        this.description = board.getDescription();
        this.createdAt = board.getCreateAt();
        this.modifiedAt = board.getModifiedAt();
    }
}
