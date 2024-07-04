package org.example.boardservice.service;

import lombok.RequiredArgsConstructor;
import org.example.boardservice.DTO.BoardDTO;
import org.example.boardservice.Entity.BoardEntity;
import org.example.boardservice.repository.BoardRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImple implements BoardService {

    private final BoardRepository repository;

    @Override
    public Long registerBoard(BoardDTO dto) {

        System.out.println("register====");

        BoardEntity board = dtoToEntity(dto);

        repository.save(board);

        return board.getBno();
    }
}
