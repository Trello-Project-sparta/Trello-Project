package com.example.trello.columnList.controller;

import com.example.trello.columnList.dto.ColumnListRequestDto;
import com.example.trello.columnList.dto.ColumnListResponseDto;
import com.example.trello.columnList.dto.SequenceResponseDto;
import com.example.trello.columnList.service.ColumnListService;
import com.example.trello.common.dto.ResponseDto;
import com.example.trello.security.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards/{boardId}/columns")
public class ColumnListController {

    private final ColumnListService columnListService;

    @PostMapping()
    public ResponseEntity<ColumnListResponseDto> createColumn(@PathVariable Long boardId,
        @RequestBody ColumnListRequestDto columnListRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ColumnListResponseDto columnListResponseDto = columnListService.createColumn(boardId,
            columnListRequestDto, userDetails.getUser());
        return ResponseEntity.ok().body(columnListResponseDto);
    }

    @PatchMapping("/{sequence}")
    public ResponseEntity<ColumnListResponseDto> updateColumn(@PathVariable Long boardId,
        @PathVariable Integer sequence, @AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestBody ColumnListRequestDto columnListRequestDto) {
        ColumnListResponseDto columnListResponseDto = columnListService.updateColumn(boardId,
            sequence, userDetails.getUser(), columnListRequestDto);
        return ResponseEntity.ok().body(columnListResponseDto);
    }

    @DeleteMapping("/{sequence}")
    public ResponseEntity<ResponseDto<String>> deleteColumn(@PathVariable Long boardId,
        @PathVariable Integer sequence, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        columnListService.deleteColumn(boardId,
            sequence, userDetails.getUser());
        return ResponseEntity.ok().body(ResponseDto.success(HttpStatus.OK.value(), "컬럼 삭제 성공"));
    }

    @PutMapping("/{sequence}")
    public ResponseEntity<List<SequenceResponseDto>> moveColumn(
        @RequestParam(name = "move") Integer move,
        @PathVariable Long boardId,
        @PathVariable Integer sequence,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<SequenceResponseDto> sequenceResponseDtoList = columnListService.moveColumn(move,
            boardId,
            userDetails.getUser(), sequence);
        return ResponseEntity.ok().body(sequenceResponseDtoList);
    }
}
