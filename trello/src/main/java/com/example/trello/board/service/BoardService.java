package com.example.trello.board.service;

import com.example.trello.board.dto.BoardRequestDto;
import com.example.trello.board.dto.BoardResponseDto;
import com.example.trello.board.entity.Board;
import com.example.trello.board.repository.BoardRepository;
import com.example.trello.global.exception.InvalidUserException;
import com.example.trello.global.exception.NotFoundBoardException;
import com.example.trello.global.exception.NotFoundUserBoardException;
import com.example.trello.user.User;
import com.example.trello.userBoard.entity.UserBoard;
import com.example.trello.userBoard.entity.UserRoleEnum;
import com.example.trello.userBoard.repository.UserBoardRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserBoardRepository userBoardRepository;

    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto, User user) {
        Board board = new Board(boardRequestDto.getTitle(), boardRequestDto.getBackground_color(),
            boardRequestDto.getDescription());

        Board saveBoard = boardRepository.save(board);

        UserBoard userBoard = new UserBoard(user, board, UserRoleEnum.HOST);
        userBoardRepository.save(userBoard);

        return new BoardResponseDto(saveBoard.getTitle(), saveBoard.getBackground_color(),
            saveBoard.getDescription(), saveBoard.getCreatedAt(), saveBoard.getModifiedAt());
    }

    @Transactional
    public BoardResponseDto updateBoard(Long boardId, BoardRequestDto boardRequestDto, User user) {
        Board board = boardRepository.findById(boardId)
            .orElseThrow(NotFoundBoardException::new);

        UserBoard userBoard = userBoardRepository.findByBoardBoardIdAndUserUserId(user.getUserId(),
                board.getBoardId())
            .orElseThrow(NotFoundUserBoardException::new);

        if (!userBoard.getUser().getUserId().equals(user.getUserId())) {
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

        UserBoard userBoard = userBoardRepository.findByBoardBoardIdAndUserUserId(user.getUserId(),
                board.getBoardId())
            .orElseThrow(NotFoundUserBoardException::new);

        if (!userBoard.getUser().getUserId().equals(user.getUserId())) {
            throw new InvalidUserException();
        }
        boardRepository.delete(board);
    }


    public List<BoardResponseDto> getAllBoard(User user, String search) {
        List<UserBoard> userBoardList = userBoardRepository.findAllByUserUserIdAndRole(
            user.getUserId(), UserRoleEnum.HOST);

        List<Long> boardIdList = new ArrayList<>();

        for (UserBoard userBoard : userBoardList) {
            boardIdList.add(userBoard.getBoard().getBoardId());
        }

        List<Board> boardList = boardRepository.searchByBoardIdIn(boardIdList, search);

        return boardList.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }


    public Board findById(Long boardId) {
        return boardRepository.findById(boardId)
            .orElseThrow(NotFoundBoardException::new);
    }

}
