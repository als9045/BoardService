package org.example.boardservice.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private User writer;

}
