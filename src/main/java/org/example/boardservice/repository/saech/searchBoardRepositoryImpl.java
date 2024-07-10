package org.example.boardservice.repository.saech;

import com.querydsl.jpa.JPQLQuery;
import org.example.boardservice.Entity.Board;
import org.example.boardservice.Entity.QBoard;
import org.example.boardservice.Entity.QReply;
import org.example.boardservice.Entity.QUser;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class searchBoardRepositoryImpl extends QuerydslRepositorySupport implements searchBoardRepository {

    public searchBoardRepositoryImpl(){
        super(Board.class);


    }

    @Override
    public Board search1() {
        System.out.println("serch1........");

        QBoard board = QBoard.board;
        QUser user = QUser.user;
        QReply reply = QReply.reply;


        JPQLQuery<Board> jpqlQueryquery = from(board);
        jpqlQueryquery.leftJoin(user).on(board.writer.eq(user));
        jpqlQueryquery.leftJoin(reply).on(reply.board.eq(board));

        jpqlQueryquery.select(board, user.email, reply.count()).groupBy(board);

        System.out.println("jpqlQueryquery........"+jpqlQueryquery);

        List<Board> result = jpqlQueryquery.fetch();
        return null;
    }
}
