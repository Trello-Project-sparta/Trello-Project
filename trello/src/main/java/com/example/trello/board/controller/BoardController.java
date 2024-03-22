package com.example.trello.board.controller;

import com.example.trello.board.dto.BoardRequestDto;
import com.example.trello.board.dto.BoardResponseDto;
import com.example.trello.board.service.BoardService;
import com.example.trello.common.dto.ResponseDto;
import com.example.trello.security.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("")
    public ResponseEntity<BoardResponseDto> createBoard(
        @RequestBody BoardRequestDto boardRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok()
            .body(boardService.createBoard(boardRequestDto, userDetails.getUser()));
    }

    @PatchMapping("/{boardId}")
    public ResponseEntity<BoardResponseDto> updateBoard(@PathVariable Long boardId,
        @RequestBody BoardRequestDto boardRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok()
            .body(boardService.updateBoard(boardId, boardRequestDto, userDetails.getUser()));
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<ResponseDto<String>> deleteBoard(@PathVariable Long boardId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        boardService.deleteBoard(boardId, userDetails.getUser());
        return ResponseEntity.ok().body(ResponseDto.success(HttpStatus.OK.value(), "보드를 삭제했습니다."));
    }

    @GetMapping("")
    public List<BoardResponseDto> getAllBoard(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestParam(name = "search", defaultValue = "") String search) {
        return boardService.getAllBoard(userDetails.getUser(), search);
    }

}
