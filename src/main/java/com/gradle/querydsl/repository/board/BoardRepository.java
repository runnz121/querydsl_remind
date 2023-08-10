package com.gradle.querydsl.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gradle.querydsl.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
}
