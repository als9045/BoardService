package org.example.boardservice.repository;


import org.example.boardservice.Entity.Board;
import org.example.boardservice.repository.saech.searchBoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long>, searchBoardRepository {

    @Query("select b, w from Board b left join b.writer w where b.bno =:bno")
    Object getBoardWiriter(@Param("bno") Long bno);

    @Query("select b, r from Board b left join Reply r on r.board = b where b.bno =:bno")
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);

    @Query(value = "select b, w, count(r) " +
            "from Board b " +
            "LEFT join b.writer w " +
            "left join Reply r on r.board = b " +
            "group by b",
            countQuery = "select count(b) from Board b") //페이징 처리 시 전체 데이터 개수를 계산하여 총 페이지 수를 정확히 산출하기 위함
    Page<Object[]> getBoardCount(Pageable pageable);//목록화면에 필요한 데이터

    @Query("select b, w, count(r) " +
           " from Board b left join b.writer w " +
            " left outer join Reply r on r.board = b " +
            " where b.bno =:bno")
    Object getBoardByBno(@Param("bno") Long bno);
} 
