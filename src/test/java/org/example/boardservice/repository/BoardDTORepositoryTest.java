package org.example.boardservice.repository;

import org.example.boardservice.DTO.BoardDTO;
import org.example.boardservice.Entity.Board;
import org.example.boardservice.Entity.User;
import org.example.boardservice.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardDTORepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

    @Test
    public void insertBoard() {

        IntStream.rangeClosed(1, 100).forEach(i -> {

            User member = User.builder().email("user"+i+"@aas.com").build();

            Board board = Board.builder()
                    .title("Title...."+i)
                    .content("content...."+i)
                    .writer(member)
                    .build();

            boardRepository.save(board);
        });

    }

    @Test
    public void test2(){

        Object result = boardRepository.getBoardWiriter(100L);

        Object[] arr = (Object[]) result;

        System.out.println("------------------");
        System.out.println(Arrays.toString(arr));
    }


    @Test
    public void test3(){

        List<Object[]> result = boardRepository.getBoardWithReply(100L);

        for(Object[] arr : result){
            System.out.println(Arrays.toString(arr));
        }
    }

    @Test
    public void test4(){

        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());

        Page<Object[]> result = boardRepository.getBoardCount(pageable);

        result.get().forEach(row ->{

            Object[] arr = (Object[]) row;
            System.out.println(Arrays.toString(arr));
        });

    }

    @Test
    public void test5(){

        BoardDTO dto = BoardDTO.builder()
                .title("Test")
                .content("Cpmtemt")
                .writerEmail("user10@aas.com")
                .build();

        Long bno = boardService.registerBoard(dto);

    }
}
