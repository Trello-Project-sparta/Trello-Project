package com.example.trello.team.service;

import com.example.trello.board.entity.Board;
import com.example.trello.board.service.BoardService;
import com.example.trello.global.NotfoundUserException;
import com.example.trello.team.dto.TeamRequestDto;
import com.example.trello.team.dto.TeamResponseDto;
import com.example.trello.team.entity.Team;
import com.example.trello.team.entity.UserRoleEnum;
import com.example.trello.team.repository.TeamRepository;
import com.example.trello.user.User;
import com.example.trello.user.service.UserService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final BoardService boardService;
    private final UserService userService;
    private final TeamRepository teamRepository;

    @Transactional
    public TeamResponseDto addCollaborator(Long boardId,
        TeamRequestDto teamRequestDto) {
        Board board = boardService.findById(boardId);

        List<String> emailList = new ArrayList<>();

        List<User> userList = userService.findAllByEmailIn(teamRequestDto.getEmailList());

        List<Team> teamList = new ArrayList<>();

        if (!teamRepository.existsByUserInAndBoardBoardId(userList, boardId)) {
            for (User user : userList) {
                teamList.add(new Team(user, board, UserRoleEnum.GUEST));
            }
            teamRepository.saveAll(teamList);
        }

        List<Team> savedTeamList = teamRepository.findAllByBoardBoardId(boardId);

        for (Team savedTeam : savedTeamList) {
            emailList.add(savedTeam.getUser().getUsername());
        }

        return new TeamResponseDto(emailList, board.getTitle());
    }

    @Transactional
    public TeamResponseDto deleteCollaborator(Long boardId,
        TeamRequestDto teamRequestDto) {
        Board board = boardService.findById(boardId);

        List<String> emailList = new ArrayList<>();

        List<User> userList = userService.findAllByEmailIn(teamRequestDto.getEmailList());

        List<Team> teamList = teamRepository.findAllByUserInAndBoardBoardId(userList, boardId);

        if (teamRepository.existsByUserInAndBoardBoardId(userList, boardId)) {
            teamRepository.deleteAll(teamList);
        } else {
            throw new NotfoundUserException();
        }

        List<Team> savedTeamList = teamRepository.findAllByBoardBoardId(boardId);

        for (Team savedTeam : savedTeamList) {
            emailList.add(savedTeam.getUser().getUsername());
        }

        return new TeamResponseDto(emailList, board.getTitle());
    }
}
