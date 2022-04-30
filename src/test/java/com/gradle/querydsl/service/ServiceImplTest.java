package com.gradle.querydsl.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.gradle.querydsl.domain.ClassDTO;
import com.gradle.querydsl.domain.Users;
import com.gradle.querydsl.repository.Food.FoodRepository;
import com.gradle.querydsl.repository.Food.FoodRepositoryCustom;
import com.gradle.querydsl.repository.Food.FoodRepositoryImpl;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class ServiceImplTest {

	@Autowired
	private ServiceImpl serviceImpl;

	@Autowired
	private FoodRepository foodRepository;


	@Test
	public void getUser() {

		ClassDTO.condition keyword = ClassDTO.condition
			.builder()
			.key("key")
			.build();

		List<ClassDTO.UserVO> userList = serviceImpl.getUser(keyword);

		for(ClassDTO.UserVO users : userList) {
			System.out.println(users);
		}
	}

	@Test
	public void joinTest() {

		ClassDTO.condition keyword =
			ClassDTO.condition
				.builder()
				.key("key")
				.build();

		List<ClassDTO.UserFoodVo> lists = serviceImpl.getJoin(keyword);

		for (ClassDTO.UserFoodVo lis : lists) {
			System.out.print(lis.getFoodName() + " " + lis.getUserName());
			System.out.println();

		}
	}

	@DisplayName("deprecated pagin query")
	@Test
	public void deprecatedPaging() {

		ClassDTO.condition condition1 = ClassDTO.condition
			.builder()
			.key("key")
			.size(4)
			.page(1)
			.build();

		ClassDTO.condition condition2 = ClassDTO.condition
			.builder()
			.key("key")
			.size(3)
			.page(1)
			.build();

		PageImpl<ClassDTO.UserFoodVo> lists1 = foodRepository.pagingList(condition1);
		PageImpl<ClassDTO.UserFoodVo> lists2 = foodRepository.pagingList(condition2);

		for (ClassDTO.UserFoodVo lis: lists1) {
			System.out.print(lis.getFoodName() + " " + lis.getUserName());
			System.out.println();
		}

		for (ClassDTO.UserFoodVo lis : lists2) {
			System.out.println(lis.getFoodName() + " " + lis.getUserName());
			System.out.println();
		}

		Assertions.assertThat(lists1.getSize()).isEqualTo(4);
		Assertions.assertThat(lists2.getSize()).isEqualTo(3);
	}


	@DisplayName("new paging query")
	@Test
	public void newPagingQuery() {

		ClassDTO.condition condition1 = ClassDTO.condition
			.builder()
			.key("key")
			.size(4)
			.page(1)
			.build();

		ClassDTO.condition condition2 = ClassDTO.condition
			.builder()
			.key("key")
			.size(3)
			.page(1)
			.build();

		//Pageable pageable =  PageRequest.of(0, 10, Sort.by("createdAt").descending());
		Pageable pageableSize3 = PageRequest.of(0,3);
		Pageable pageableSize4 = PageRequest.of(0,4);

		Page<ClassDTO.UserFoodVo> lists1 = foodRepository.paginList2(condition1, pageableSize3);
		Page<ClassDTO.UserFoodVo> lists2 = foodRepository.paginList2(condition2, pageableSize4);

		for (ClassDTO.UserFoodVo lis: lists1) {
			System.out.print(lis.getFoodName() + " " + lis.getUserName());
			System.out.println();
		}

		for (ClassDTO.UserFoodVo lis : lists2) {
			System.out.println(lis.getFoodName() + " " + lis.getUserName());
			System.out.println();
		}

		Assertions.assertThat(lists1.getSize()).isEqualTo(3);
		Assertions.assertThat(lists2.getSize()).isEqualTo(4);
	}

}