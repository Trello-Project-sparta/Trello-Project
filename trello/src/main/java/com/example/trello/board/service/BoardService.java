package com.example.trello.board.service;

import com.example.trello.board.dto.BoardRequestDto;
import com.example.trello.board.dto.BoardResponseDto;
import com.example.trello.board.entity.Board;
import com.example.trello.board.repository.BoardRepository;
import com.example.trello.global.exception.InvalidUserException;
import com.example.trello.global.exception.NotFoundBoardException;
import com.example.trello.global.exception.NotFoundTeamException;
import com.example.trello.team.entity.Team;
import com.example.trello.team.entity.UserRoleEnum;
import com.example.trello.team.repository.TeamRepository;
import com.example.trello.user.User;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto, User user) {
        Board board = new Board(boardRequestDto.getTitle(), boardRequestDto.getBackground_color(),
            boardRequestDto.getDescription());

        Board saveBoard = boardRepository.save(board);

        Team team = new Team(user, board, UserRoleEnum.HOST);
        teamRepository.save(team);

        return new BoardResponseDto(saveBoard.getTitle(), saveBoard.getBackground_color(),
            saveBoard.getDescription(), saveBoard.getCreatedAt(), saveBoard.getModifiedAt());
    }

    @Transactional
    public BoardResponseDto updateBoard(Long boardId, BoardRequestDto boardRequestDto, User user) {
        Board board = boardRepository.findById(boardId)
            .orElseThrow(NotFoundBoardException::new);

        Team team = teamRepository.findByUserAndBoard(user, board)
            .orElseThrow(NotFoundTeamException::new);

        if (!team.getUser().getUserId().equals(user.getUserId())) {
            throw new InvalidUserException();
        }

        board.update(boardRequestDto.getTitle(), boardRequestDto.getBackground_color(),
            boardRequestDto.getDescription());

        return new BoardResponseDto(board.getTitle(), board.getBackground_color(),
            board.getDescription(), board.getCreatedAt(),
            board.getModifiedAt());
    }

    public void deleteBoard(Long boardId, User user) {
        Board board = boardRepository.findById(boardId)
            .orElseThrow(NotFoundBoardException::new);

        Team team = teamRepository.findByUserAndBoard(user, board)
            .orElseThrow(NotFoundTeamException::new);

        if (!team.getUser().getUserId().equals(user.getUserId())) {
            throw new InvalidUserException();
        }
        boardRepository.delete(board);
    }


    public Board findById(Long boardId) {
        return boardRepository.findById(boardId)
            .orElseThrow(NotFoundBoardException::new);
    }

    public List<BoardResponseDto> searchBoard(String search) {
        List<Board> boardList = boardRepository.searchByAny(search);

        return boardList.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }
}
