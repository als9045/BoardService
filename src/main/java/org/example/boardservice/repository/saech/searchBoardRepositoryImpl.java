package org.example.boardservice.repository.saech;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import org.example.boardservice.Entity.Board;
import org.example.boardservice.Entity.QBoard;
import org.example.boardservice.Entity.QReply;
import org.example.boardservice.Entity.QUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {

        System.out.println("searchPage.......");

        QBoard board = QBoard.board;
        QUser user = QUser.user;
        QReply reply = QReply.reply;

        JPQLQuery<Board> jpqlQueryquery = from(board);
        jpqlQueryquery.leftJoin(user).on(board.writer.eq(user));
        jpqlQueryquery.leftJoin(reply).on(reply.board.eq(board));

        JPQLQuery<Tuple> tuple = jpqlQueryquery.select(board,user,reply.count());

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = board.bno.gt(0L);

        booleanBuilder.and(expression);

        if(type != null){
            String[] typeArr = type.split(",");

            BooleanBuilder condition = new BooleanBuilder();

            for(String t:typeArr){
                switch (t){
                    case "t":
                        condition.or(board.title.contains(keyword));
                        break;
                    case "w":
                        condition.or(user.email.contains(keyword));
                        break;
                    case "c":
                        condition.or(board.content.contains(keyword));
                        break;
                }
            }
            booleanBuilder.and(condition);
        }
        tuple.where(booleanBuilder);

        tuple.groupBy(board);

        this.getQuerydsl().applyPagination(pageable,tuple);

        List<Tuple> result = tuple.fetch();

        System.out.println("result = "+result);

        Long count = tuple.fetchCount();

        System.out.println("count = "+count);

        return new PageImpl<Object[]>(
                result.stream().map(Tuple::toArray).collect(Collectors.toList()),
                pageable,count
        );
    }
}
