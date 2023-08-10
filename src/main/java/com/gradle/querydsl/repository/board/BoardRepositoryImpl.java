package com.gradle.querydsl.repository.board;

import static com.gradle.querydsl.domain.QBoard.*;
import static com.gradle.querydsl.domain.QComments.*;
import static com.querydsl.core.group.GroupBy.*;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.gradle.querydsl.domain.BoardDto;
import com.gradle.querydsl.domain.QBoard;
import com.gradle.querydsl.domain.QBoardDto;
import com.gradle.querydsl.domain.QComments;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.sql.JPASQLQuery;

@Repository
public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final JPASQLQuery jpasqlQuery;

    public BoardRepositoryImpl(JPAQueryFactory jpaQueryFactory, JPASQLQuery jpasqlQuery) {
        this.jpaQueryFactory = jpaQueryFactory;
        this.jpasqlQuery = jpasqlQuery;
    }
    @Override
    public Page<BoardDto> findBoards(Pageable page) {
        QBoard board = QBoard.board;
        QComments comments = QComments.comments;

        QBoard board_comments1 = new QBoard("board_comments");
        QComments board_comments2 = new QComments("board_comments");

        //https://github.com/querydsl/querydsl/issues/2809
        List<BoardDto> results = jpasqlQuery
            .select(
                new QBoardDto(
                    board.id,
                    board.name))
            .from(board)
            .leftJoin(board_comments1).on(board.id.eq(board_comments1.id))
            .fetch();

        System.out.println(results);

        // List<BoardDto> results = jpaQueryFactory.select(new QBoardDto(
        //     board.id,
        //     board.name,
        //     boardComments.countDistinct()
        //     ))
        //     .from(board)
        //     .leftJoin(boardComments).on(board.id.eq(boardComments.id))
        //     .offset(page.getOffset())
        //     .limit(page.getPageSize())
        //     .fetch();

        long total = jpaQueryFactory
            .select(board.id)
            .from(board)
            .fetch().size();
        //
        // return new PageImpl<>(results, page, total);

        return null;
    }
}
