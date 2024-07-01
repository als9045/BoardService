package org.example.boardservice.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long BoardNum;

    private String title;

    private String content;

    @ManyToOne
    private User Writer;

}
