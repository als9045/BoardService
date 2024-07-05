package org.example.boardservice.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class User extends baseEntity {

    @Id
    private String email;

    private String password;

    private String NickName;

    private String name;

}
