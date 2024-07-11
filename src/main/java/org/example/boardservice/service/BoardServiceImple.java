package org.example.boardservice.service;

import lombok.RequiredArgsConstructor;
import org.example.boardservice.DTO.BoardDTO;
import org.example.boardservice.DTO.PageRequestDTO;
import org.example.boardservice.DTO.PageResultDTO;
import org.example.boardservice.Entity.Board;
import org.example.boardservice.Entity.User;
import org.example.boardservice.repository.BoardRepository;
import org.example.boardservice.repository.ReplyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class BoardServiceImple implements BoardService {

    private final BoardRepository repository;

    private final ReplyRepository replyRepository;

    @Override
    public Long registerBoard(BoardDTO dto) {

        System.out.println("register====");

        Board board = dtoToEntity(dto);

        repository.save(board);

        return board.getBno();
    }


    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        System.out.println("SerivceImpe= "+pageRequestDTO);
        Function<Object[], BoardDTO> fn = (en -> entityToDto((Board)en[0], (User)en[1], (Long)en[2]));

        Page<Object[]> result = repository.getBoardCount(pageRequestDTO.getPageable(Sort.by("bno").descending()));

        Page<Object[]> resutl = repository.searchPage(
                pageRequestDTO.getType(),
                pageRequestDTO.getKeyword(),
                pageRequestDTO.getPageable(Sort.by("bno").descending())
        );

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public BoardDTO get(Long bno) {

        Object result = repository.getBoardByBno(bno);

        Object[] arr = (Object[])result;

        return entityToDto((Board)arr[0], (User)arr[1], (Long)arr[2]);
    }

    @Transactional
    @Override
    public void removeWithReplies(Long bno) {

        replyRepository.deleteByBno(bno);

        repository.deleteById(bno);


    }

    @Override
    public void modify(BoardDTO boardDTO) {

        Board board = repository.getReferenceById(boardDTO.getBno());

        if(board != null){

            board.changeTitle(boardDTO.getTitle());
            board.changeContent(boardDTO.getContent());

            repository.save(board);
        }
    }


}
