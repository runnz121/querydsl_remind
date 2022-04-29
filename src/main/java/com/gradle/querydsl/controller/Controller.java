package com.gradle.querydsl.controller;

import java.util.List;

import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gradle.querydsl.domain.ClassDTO;
import com.gradle.querydsl.service.ServiceImpl;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class Controller {

	private final ServiceImpl serviceImpl;

	@GetMapping("/healthCheck")
	public ModelAndView check(Model model) {

		ModelAndView modelAndView = new ModelAndView("health");

		return modelAndView;
	}

	//user data 반환
	@GetMapping("/users")
	public ResponseEntity<List<ClassDTO.UserVO>> getUser(ClassDTO.condition dto) {
		List<ClassDTO.UserVO> res = serviceImpl.getUser(dto);
		return ResponseEntity.ok(res);
	}

	@GetMapping("/joins")
	public ResponseEntity<?> getJoin(Model Model ) {
		List<ClassDTO.UserFoodVo> res = serviceImpl.getJoin();

		ModelAndView modelAndView = new ModelAndView("list");

		return ResponseEntity.ok(res);
	}

	@GetMapping("/three")
	public ResponseEntity<?> three() {
		List<ClassDTO.threeJoin> lists = serviceImpl.threeJoin();
		return ResponseEntity.ok(lists);
	}
}
