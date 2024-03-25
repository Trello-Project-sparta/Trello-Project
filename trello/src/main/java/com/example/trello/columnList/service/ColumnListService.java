package com.example.trello.columnList.service;

import com.example.trello.board.entity.Board;
import com.example.trello.board.service.BoardService;
import com.example.trello.columnList.dto.ColumnListRequestDto;
import com.example.trello.columnList.dto.ColumnListResponseDto;
import com.example.trello.columnList.dto.SequenceResponseDto;
import com.example.trello.columnList.entity.ColumnList;
import com.example.trello.columnList.repository.ColumnListRepository;
import com.example.trello.global.exception.NotFoundColumnListException;
import com.example.trello.user.entity.User;
import com.example.trello.userBoard.repository.UserBoardRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ColumnListService {

    private final ColumnListRepository columnListRepository;
    private final UserBoardRepository userBoardRepository;
    private final BoardService boardService;

    public ColumnListResponseDto createColumn(Long boardId,
        ColumnListRequestDto columnListRequestDto, User user) {
        validateInvitedUser(boardId, user.getUserId());

        Board board = boardService.findById(boardId);

        List<ColumnList> columnLists = columnListRepository.findAllByBoardBoardIdOrderBySequenceDesc(
            boardId);

        ColumnList columnList;

        if (columnListRepository.findAllByBoardBoardId(boardId).isEmpty()) {
            columnList = new ColumnList(columnListRequestDto.getTitle(), 1, board);
        } else {
            columnList = new ColumnList(columnListRequestDto.getTitle(),
                columnLists.get(0).getSequence() + 1, board);
        }

        columnListRepository.save(columnList);

        return new ColumnListResponseDto(columnList.getTitle(), columnList.getSequence(),
            columnList.getBoard().getTitle());
    }

    @Transactional
    public ColumnListResponseDto updateColumn(Long boardId, Integer sequence, User user,
        ColumnListRequestDto columnListRequestDto) {
        validateInvitedUser(boardId, user.getUserId());

        ColumnList columnList = columnListRepository.findByBoardBoardIdAndSequence(boardId,
            sequence).orElseThrow(NotFoundColumnListException::new);

        columnList.updateTitle(columnListRequestDto.getTitle());

        return new ColumnListResponseDto(columnList.getTitle(), columnList.getSequence(),
            columnList.getBoard().getTitle());
    }

    @Transactional
    public void deleteColumn(Long boardId, Integer sequence, User user) {
        validateInvitedUser(boardId, user.getUserId());

        List<ColumnList> columnLists = columnListRepository.searchAllMoreThanSearch(sequence);

        ColumnList columnList = columnListRepository.findByBoardBoardIdAndSequence(boardId,
            sequence).orElseThrow();

        columnListRepository.delete(columnList);

        for (ColumnList column : columnLists) {
            column.move(column.getSequence() - 1);
        }
    }


    @Transactional
    public List<SequenceResponseDto> moveColumn(Integer move, Long boardId, User user,
        Integer sequence) {
        validateInvitedUser(boardId, user.getUserId());

        ColumnList columnList = columnListRepository.findByBoardBoardIdAndSequence(boardId,
            sequence).orElseThrow();

        List<ColumnList> columnLists = columnListRepository.searchAllMoreThanSearchAndLessThanSequence(
            move,
            columnList.getSequence());

        columnList.move(move);

        List<SequenceResponseDto> sequenceResponseDtos = new ArrayList<>();

        for (ColumnList column : columnLists) {
            column.move(column.getSequence() + 1);
            sequenceResponseDtos.add(new SequenceResponseDto(column.getSequence()));
        }

        return sequenceResponseDtos;
    }


    private void validateInvitedUser(Long boardId, Long userId) {
        if (userBoardRepository.findByBoardBoardIdAndUserUserId(boardId, userId)
            .isEmpty()) {
            throw new IllegalArgumentException("당신은 이 보드에 초대되어있지 않습니다.");
        }
    }
}
