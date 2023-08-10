package com.gradle.querydsl.repository.Food;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.gradle.querydsl.domain.ClassDTO;
import com.gradle.querydsl.domain.QClassDTO_UserFoodVo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import static com.gradle.querydsl.domain.QUsers.*;
import static com.gradle.querydsl.domain.QFood.*;
import static com.gradle.querydsl.domain.QCoolTime.*;

@Repository
@RequiredArgsConstructor
public class FoodRepositoryImpl implements FoodRepositoryCustom{

	private final JPAQueryFactory jpaQueryFactory;


	@Override
	public List<ClassDTO.UserFoodVo> foodList(ClassDTO.condition search) {
		List<ClassDTO.UserFoodVo> lists =  jpaQueryFactory.
			select(Projections.constructor(ClassDTO.UserFoodVo.class, users.userName, food.name))
			.from(food)
			.join(coolTime)
			.on(food.id.eq(coolTime.foodId))
			.join(users)
			.on(users.id.eq(coolTime.userId))
			.where(searchPredicate(search)) //where 조건 추가 후 하위에 where 조건 선택
			.fetch()
			;

		return lists;
	}

	//paging 처리 ->List로 받지않고 pageImpl로 받음
	@Override
	public PageImpl<ClassDTO.UserFoodVo> pagingList(ClassDTO.condition search) {

		Integer page = search.getPage() - 1; //페이지
		Integer size = search.getSize(); //한번에 보낼 청크 사이즈

		//반환값이 리스트가 아닌 queryResults
		QueryResults<ClassDTO.UserFoodVo> paginglists = jpaQueryFactory
			.select(Projections.constructor(ClassDTO.UserFoodVo.class,
				users.userName,
				food.name))
			.from(food)
			.join(coolTime)
			.on(food.id.eq(coolTime.foodId))
			.join(users)
			.on(users.id.eq(coolTime.userId))
			//페이징 처리 하는 부분
			.offset(page * size)
			.limit(size)
			// --
			.fetchResults(); // fetchResults() -> depreacated 되었음으로 select와 별도로 count 쿼리를 작성

		PageImpl<ClassDTO.UserFoodVo> returnList =
			new PageImpl<>(
				paginglists.getResults(),
				PageRequest.of(page, size),
				paginglists.getTotal());
		return returnList;
	}


	/**
	 *
	 * @param 위의 depreacate 메소드를 수정
	 * @return 상위 메소드와 동일한 결과값을 반환함
	 */

	@Override
	public List<ClassDTO.UserFoodVo> paginList2(ClassDTO.condition search, Pageable pageable) {

		// List<ClassDTO.UserFoodVo> pagingList = jpaQueryFactory
		// 	// @AllArgsConstructor -> @QueryProjection 붙임
		// 	.select(new QClassDTO_UserFoodVo (
		//
		// 		))

		return null;
	}







	//where 조건을 빌더로 생성
	public static Predicate searchPredicate(ClassDTO.condition search) {
		BooleanBuilder builder = new BooleanBuilder();

		if (search.getKeyWord() == null) {
			builder.and(users.nickName.eq(search.getKeyWord()));
		}
		return builder;
	}

}
