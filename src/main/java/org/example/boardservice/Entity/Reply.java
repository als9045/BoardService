package org.example.boardservice.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "boardEntity")
public class Reply extends baseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Num;

    private String text;

    private String replyer;

    @ManyToOne
    private BoardEntity boardEntity;




}
