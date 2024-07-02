package org.example.boardservice.repository;

import org.example.boardservice.Entity.Board;
import org.example.boardservice.Entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

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
}
