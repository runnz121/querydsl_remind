package com.gradle.querydsl.repository.Users;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gradle.querydsl.domain.Food;
import com.gradle.querydsl.domain.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
