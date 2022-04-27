package com.gradle.querydsl.repository.Food;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gradle.querydsl.domain.ClassDTO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import static com.gradle.querydsl.domain.QUsers.*;
import static com.gradle.querydsl.domain.QFood.*;
import static com.gradle.querydsl.domain.QCoolTime.*;

import javax.swing.text.html.parser.Entity;

@Repository
@RequiredArgsConstructor
public class FoodRepositoryImpl implements FoodRepositoryCustom{

	private final JPAQueryFactory jpaQueryFactory;


	@Override
	public List<ClassDTO.UserFoodVo> foodList() {
		List<ClassDTO.UserFoodVo> lists =  jpaQueryFactory.
			select(Projections.constructor(ClassDTO.UserFoodVo.class, users.userName, food.name))
			.from(food)
			.join(coolTime)
			.on(food.id.eq(coolTime.foodId))
			.join(users)
			.on(users.id.eq(coolTime.userId))
			.fetch()
			;

		return lists;

	}
}
