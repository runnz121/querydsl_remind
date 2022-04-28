package com.gradle.querydsl.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gradle.querydsl.domain.ClassDTO;
import com.gradle.querydsl.service.ServiceImpl;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class Controller {

	private final ServiceImpl serviceImpl;

	@GetMapping("/healthCheck")
	public String check() {
		return "health check";
	}

	//user data 반환
	@GetMapping("/users")
	public ResponseEntity<List<ClassDTO.UserVO>> getUser(ClassDTO.condition dto) {
		List<ClassDTO.UserVO> res = serviceImpl.getUser(dto);
		return ResponseEntity.ok(res);
	}

	@GetMapping("/joins")
	public ResponseEntity<?> getJoin(ClassDTO.condition dto) {
		List<ClassDTO.UserFoodVo> res = serviceImpl.getJoin(dto);
		return ResponseEntity.ok(res);
	}

	@GetMapping("/three")
	public ResponseEntity<?> three() {
		List<ClassDTO.threeJoin> lists = serviceImpl.threeJoin();
		return ResponseEntity.ok(lists);
	}
}
