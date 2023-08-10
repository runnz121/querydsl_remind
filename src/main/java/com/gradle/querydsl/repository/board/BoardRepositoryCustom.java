package com.gradle.querydsl.repository.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gradle.querydsl.domain.BoardDto;

public interface BoardRepositoryCustom {

    Page<BoardDto> findBoards(Pageable page);
}
