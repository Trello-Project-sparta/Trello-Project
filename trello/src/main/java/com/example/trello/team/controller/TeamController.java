package com.example.trello.team.controller;

import com.example.trello.team.dto.TeamRequestDto;
import com.example.trello.team.dto.TeamResponseDto;
import com.example.trello.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class TeamController {

    private final TeamService teamService;

    @PutMapping("/{boardId}/collaborators")
    public ResponseEntity<TeamResponseDto> addCollaborator(@PathVariable Long boardId,
        @RequestBody TeamRequestDto teamRequestDto) {
        TeamResponseDto userBoard = teamService.addCollaborator(boardId, teamRequestDto);
        return ResponseEntity.ok().body(userBoard);
    }

    @DeleteMapping("/{boardId}/collaborators")
    public ResponseEntity<TeamResponseDto> deleteCollaborator(@PathVariable Long boardId,
        @RequestBody TeamRequestDto teamRequestDto) {
        TeamResponseDto teamResponseDto = teamService.deleteCollaborator(boardId, teamRequestDto);
        return ResponseEntity.ok().body(teamResponseDto);
    }
}
