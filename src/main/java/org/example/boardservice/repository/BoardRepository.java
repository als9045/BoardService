package org.example.boardservice.repository;


import org.example.boardservice.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardRepository extends JpaRepository<Board, Long> {
}
