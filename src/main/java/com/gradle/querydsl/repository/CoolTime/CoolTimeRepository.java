package com.gradle.querydsl.repository.CoolTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gradle.querydsl.domain.CoolTime;
import com.gradle.querydsl.domain.Food;

public interface CoolTimeRepository extends JpaRepository<CoolTime, Long> {
}
