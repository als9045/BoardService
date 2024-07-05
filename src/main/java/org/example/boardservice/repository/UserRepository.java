package org.example.boardservice.repository;

import org.example.boardservice.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}

