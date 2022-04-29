package com.gradle.querydsl.repository.Food;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gradle.querydsl.domain.ClassDTO;
import com.gradle.querydsl.domain.Food;
import com.querydsl.core.Tuple;

public interface FoodRepositoryCustom {

	List<ClassDTO.UserFoodVo> foodList(ClassDTO.condition search);

	//deprecated 된 메소드가 포함됨
	PageImpl<ClassDTO.UserFoodVo> pagingList(ClassDTO.condition search);

	//위의 메소드와 동일한 결과값을 반환하는 최신 문법
	List<ClassDTO.UserFoodVo> paginList2(ClassDTO.condition search, Pageable pageable);
}
