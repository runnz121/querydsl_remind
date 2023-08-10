package com.gradle.querydsl.domain;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BoardDto {

    private Long id;

    private String boardName;

    private Long commentCounts;

    @QueryProjection
    public BoardDto(Long id, String boardName) {
        this.id = id;
        this.boardName = boardName;
        // this.commentCounts = commentCounts;
    }
}
