package com.example.trello.userBoard.service;

import com.example.trello.board.entity.Board;
import com.example.trello.board.service.BoardService;
import com.example.trello.global.exception.InvalidInviteRightException;
import com.example.trello.global.exception.NotFoundUserBoardException;
import com.example.trello.global.exception.NotFoundUserException;
import com.example.trello.user.entity.User;
import com.example.trello.user.service.UserService;
import com.example.trello.userBoard.dto.UserBoardRequestDto;
import com.example.trello.userBoard.dto.UserBoardResponseDto;
import com.example.trello.userBoard.entity.UserBoard;
import com.example.trello.userBoard.entity.UserRoleEnum;
import com.example.trello.userBoard.repository.UserBoardBulkRepository;
import com.example.trello.userBoard.repository.UserBoardRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserBoardService {

    private final BoardService boardService;
    private final UserService userService;
    private final UserBoardRepository userBoardRepository;
    private final UserBoardBulkRepository userBoardBulkRepository;

    @Transactional
    public UserBoardResponseDto addCollaborator(Long boardId,
        UserBoardRequestDto userBoardRequestDto, User hostUser) {

        if (!userBoardRepository.findByBoardBoardIdAndUserUserId(boardId, hostUser.getUserId())
            .orElseThrow(NotFoundUserBoardException::new).getRole().equals(UserRoleEnum.HOST)) {
            throw new InvalidInviteRightException(hostUser.getUsername());
        }

        Board board = boardService.findById(boardId);

        List<String> emailList = new ArrayList<>();

        List<User> userList = userService.findAllByEmailIn(userBoardRequestDto.getEmailList());

        List<UserBoard> userBoardList = new ArrayList<>();

        if (!userBoardRepository.existsByUserInAndBoardBoardId(userList, boardId)) {
            for (User user : userList) {
                userBoardList.add(new UserBoard(user, board, UserRoleEnum.GUEST));
            }
            userBoardBulkRepository.saveBulk(userBoardList);

        }

        List<UserBoard> savedUserBoardList = userBoardRepository.findAllByBoardBoardId(boardId);

        for (UserBoard savedUserBoard : savedUserBoardList) {
            emailList.add(savedUserBoard.getUser().getUsername());
        }

        return new UserBoardResponseDto(emailList, board.getTitle());
    }

    @Transactional
    public UserBoardResponseDto deleteCollaborator(Long boardId,
        UserBoardRequestDto userBoardRequestDto, User hostUser) {

        if (!userBoardRepository.findByBoardBoardIdAndUserUserId(boardId, hostUser.getUserId())
            .orElseThrow().getRole().equals(UserRoleEnum.HOST)) {
            throw new InvalidInviteRightException(hostUser.getUsername());
        }

        Board board = boardService.findById(boardId);

        List<String> emailList = new ArrayList<>();

        List<User> userList = userService.findAllByEmailIn(userBoardRequestDto.getEmailList());

        List<UserBoard> userBoardList = userBoardRepository.findAllByUserInAndBoardBoardId(userList,
            boardId);

        if (userBoardRepository.existsByUserInAndBoardBoardId(userList, boardId)) {
            userBoardRepository.deleteAll(userBoardList);
        } else {
            throw new NotFoundUserException();
        }

        List<UserBoard> savedUserBoardList = userBoardRepository.findAllByBoardBoardId(boardId);

        for (UserBoard savedUserBoard : savedUserBoardList) {
            emailList.add(savedUserBoard.getUser().getUsername());
        }

        return new UserBoardResponseDto(emailList, board.getTitle());
    }
}
