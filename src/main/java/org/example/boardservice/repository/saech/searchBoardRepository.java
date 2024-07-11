package org.example.boardservice.repository.saech;


import org.example.boardservice.Entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface searchBoardRepository {

    Board search1();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}
