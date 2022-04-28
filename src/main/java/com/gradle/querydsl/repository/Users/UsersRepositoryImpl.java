package com.gradle.querydsl.repository.Users;

import static com.gradle.querydsl.domain.QUsers.*;
import static com.gradle.querydsl.domain.QCoolTime.*;
import static com.gradle.querydsl.domain.QFood.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gradle.querydsl.domain.ClassDTO;
import com.gradle.querydsl.domain.QCoolTime;
import com.gradle.querydsl.domain.Users;
import com.querydsl.core.types.Projections;
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

	@Override
	public List<ClassDTO.threeJoin> finds() {

		return jpaQueryFactory.select(Projections.fields(ClassDTO.threeJoin.class,
			users.userName,
			food.name.as("foodName"),
			coolTime.id.as("coolTimeId")))
			.from(users)
			.join(coolTime)
			.on(users.id.eq(coolTime.userId))
			.leftJoin(food)
			.on(coolTime.foodId.eq(food.id))
			.fetch();
	}
}
