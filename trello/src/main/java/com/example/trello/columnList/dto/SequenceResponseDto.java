package com.example.trello.columnList.dto;

import lombok.Getter;

@Getter
public class SequenceResponseDto {

    private Integer sequence;

    public SequenceResponseDto(Integer sequence) {
        this.sequence = sequence;
    }
}
