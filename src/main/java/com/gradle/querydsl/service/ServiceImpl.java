package com.gradle.querydsl.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gradle.querydsl.domain.ClassDTO;
import com.gradle.querydsl.domain.Users;
import com.gradle.querydsl.repository.Food.FoodRepository;
import com.gradle.querydsl.repository.Food.FoodRepositoryCustom;
import com.gradle.querydsl.repository.Users.UsersRepository;
import com.gradle.querydsl.repository.Users.UsersRepositoryCustom;
import com.querydsl.core.Tuple;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiceImpl {

	private final UsersRepositoryCustom usersRepository;

	private final FoodRepository foodRepositoryCustom;

	//userdata 반환
	@Transactional(readOnly = true)
	public List<ClassDTO.UserVO> getUser(ClassDTO.condition user) {

		List<Users> findUsers =  usersRepository.findUse();

		return findUsers.stream().map(ClassDTO.UserVO::of)
			.collect(Collectors.toList());}

	@Transactional(readOnly = true)
	public List<ClassDTO.UserFoodVo> getJoin(ClassDTO.condition cond){

		List<ClassDTO.UserFoodVo> resJoin = foodRepositoryCustom.foodList(cond);

		return resJoin;
	}

	@Transactional(readOnly = true)
	public List<ClassDTO.threeJoin> threeJoin() {
		List<ClassDTO.threeJoin> res = usersRepository.finds();
		return res;
	}
}
