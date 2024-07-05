package org.example.boardservice.repository;

import org.example.boardservice.Entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class UserDTORepositoryTest {

   @Autowired
   private UserRepository userRepository;

    @Test
    public void insertUser() {

        IntStream.rangeClosed(1,100 ).forEach(i -> {

            User user = User.builder()
                    .email("user"+i+"@aas.com")
                    .password("1234")
                    .NickName("Nick"+i)
                    .name("name"+i)
                    .build();

            userRepository.save(user);
        });
    }
}
