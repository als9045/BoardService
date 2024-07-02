package org.example.boardservice.repository;

import org.example.boardservice.Entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository  extends JpaRepository<Reply, Long> {
}
