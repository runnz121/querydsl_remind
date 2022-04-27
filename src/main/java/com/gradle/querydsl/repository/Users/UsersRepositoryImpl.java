package com.gradle.querydsl.repository.Users;

import static com.gradle.querydsl.domain.QUsers.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gradle.querydsl.domain.Users;
import com.querydsl.jpa.impl.JPAQueryFactory;


@Repository
public class UsersRepositoryImpl implements UsersRepositoryCustom{

	private final JPAQueryFactory jpaQueryFactory;

	public UsersRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
		this.jpaQueryFactory = jpaQueryFactory;
	}

	@Override
	public List<Users> findUse() {
		return jpaQueryFactory.selectFrom(users).fetch();
	}
}
