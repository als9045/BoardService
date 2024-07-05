package org.example.boardservice.service;

import org.example.boardservice.DTO.BoardDTO;
import org.example.boardservice.DTO.PageRequestDTO;
import org.example.boardservice.DTO.PageResultDTO;
import org.example.boardservice.Entity.Board;
import org.example.boardservice.Entity.Reply;
import org.example.boardservice.Entity.User;

import java.util.Objects;


public interface BoardService {

    Long registerBoard(BoardDTO dto);

    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    BoardDTO get(Long bno);

    void removeWithReplies(Long bno);

    void modify(BoardDTO boardDTO);

    default Board dtoToEntity(BoardDTO dto) {

        User user = User.builder().email(dto.getWriterEmail()).build();

        Board board = Board.builder()
                            .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(user)
                .build();
        return board;
    }

    default BoardDTO entityToDto(Board board, User user, Long replyCount) {

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegdate())
                .modDate(board.getModdate())
                .writerEmail(user.getEmail())
                .writerName(user.getName())
                .replyCount(replyCount.intValue()).build();

        return boardDTO;
    }

}
