package org.example.boardservice.repository;

import org.example.boardservice.Entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class UserEntityDTORepositoryTest {

   @Autowired
   private UserRepository userRepository;

    @Test
    public void insertUser() {

        IntStream.rangeClosed(1,100 ).forEach(i -> {

            UserEntity userEntity = UserEntity.builder()
                    .email("userEntity"+i+"@aas.com")
                    .password("1234")
                    .NickName("Nick"+i)
                    .name("name"+i)
                    .build();

            userRepository.save(userEntity);
        });
    }
}
