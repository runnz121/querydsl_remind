package com.gradle.querydsl.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gradle.querydsl.domain.ClassDTO;
import com.gradle.querydsl.domain.Users;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class ServiceImplTest {

	@Autowired
	private ServiceImpl serviceImpl;





	@Test
	public void getUser() {

		ClassDTO.condition keyword = new ClassDTO.condition("key");

		List<ClassDTO.UserVO> userList = serviceImpl.getUser(keyword);

		for(ClassDTO.UserVO users : userList) {
			System.out.println(users);
		}
	}

}