package com.example.trello.userBoard.controller;

import com.example.trello.security.UserDetailsImpl;
import com.example.trello.userBoard.dto.UserBoardRequestDto;
import com.example.trello.userBoard.dto.UserBoardResponseDto;
import com.example.trello.userBoard.service.UserBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class UserBoardController {

    private final UserBoardService userBoardService;

    @PutMapping("/{boardId}/collaborators")
    public ResponseEntity<UserBoardResponseDto> addCollaborator(@PathVariable Long boardId,
        @RequestBody UserBoardRequestDto userBoardRequestDto, @AuthenticationPrincipal
    UserDetailsImpl userDetails) {
        UserBoardResponseDto userBoard = userBoardService.addCollaborator(boardId,
            userBoardRequestDto, userDetails.getUser());
        return ResponseEntity.ok().body(userBoard);
    }

    @DeleteMapping("/{boardId}/collaborators")
    public ResponseEntity<UserBoardResponseDto> deleteCollaborator(@PathVariable Long boardId,
        @RequestBody UserBoardRequestDto userBoardRequestDto, @AuthenticationPrincipal
    UserDetailsImpl userDetails) {
        UserBoardResponseDto userBoardResponseDto = userBoardService.deleteCollaborator(boardId,
            userBoardRequestDto, userDetails.getUser());
        return ResponseEntity.ok().body(userBoardResponseDto);
    }
}
