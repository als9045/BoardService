package org.example.boardservice.service;

import org.example.boardservice.DTO.BoardDTO;
import org.example.boardservice.Entity.BoardEntity;
import org.example.boardservice.Entity.UserEntity;

import java.lang.reflect.Member;

public interface BoardService {

    Long registerBoard(BoardDTO dto);

    default BoardEntity dtoToEntity(BoardDTO dto) {

        UserEntity user = UserEntity.builder().email(dto.getWriterEmail()).build();

        BoardEntity board = BoardEntity.builder()
                            .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(user)
                .build();
        return board;
    }
}
