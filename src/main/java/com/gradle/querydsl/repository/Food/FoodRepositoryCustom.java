package com.gradle.querydsl.repository.Food;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gradle.querydsl.domain.ClassDTO;
import com.gradle.querydsl.domain.Food;
import com.querydsl.core.Tuple;

public interface FoodRepositoryCustom {

	List<ClassDTO.UserFoodVo> foodList();


}
