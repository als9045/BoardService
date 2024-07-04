package org.example.boardservice.repository;

import org.example.boardservice.Entity.BoardEntity;
import org.example.boardservice.Entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void isertReplu(){
        IntStream.rangeClosed(1,100).forEach(i -> {

            long Num = (long)(Math.random() * 100) + 1;

            BoardEntity boardEntity = BoardEntity.builder().bno(Num).build();

            Reply reply = Reply.builder()
                    .text("Reply....."+i)
                    .boardEntity(boardEntity)
                    .replyer("guest")
                    .build();
            replyRepository.save(reply);

    });
}
}