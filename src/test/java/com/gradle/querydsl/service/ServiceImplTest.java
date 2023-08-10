package com.gradle.querydsl.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gradle.querydsl.domain.Board;
import com.gradle.querydsl.domain.BoardDto;
import com.gradle.querydsl.domain.ClassDTO;
import com.gradle.querydsl.domain.Comments;
import com.gradle.querydsl.domain.Users;

import lombok.extern.slf4j.Slf4j;


@Transactional
@Rollback(false)
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class ServiceImplTest {

	@Autowired
	private ServiceImpl serviceImpl;

	@Autowired
	EntityManagerFactory emf;

	// @Test
	// public void getUser() {
	//
	// 	ClassDTO.condition keyword = new ClassDTO.condition("key");
	//
	// 	List<ClassDTO.UserVO> userList = serviceImpl.getUser(keyword);
	//
	// 	for(ClassDTO.UserVO users : userList) {
	// 		System.out.println(users);
	// 	}
	// }
	//
	// @Test
	// public void joinTest() {
	//
	// 	ClassDTO.condition keyword = new ClassDTO.condition("key");
	//
	// 	List<ClassDTO.UserFoodVo> lists = serviceImpl.getJoin(keyword);
	//
	// 	for (ClassDTO.UserFoodVo lis : lists) {
	// 		System.out.print(lis.getFoodName() + " " + lis.getUserName());
	// 		System.out.println();
	//
	// 	}
	// }

	@Test
	public void saveJoinTableEntityTest() {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		// 1
		Board board = new Board();
		board.setBoardName("boardName1");
		em.persist(board);

		Comments comments1 = new Comments();
		comments1.setComment("comments1");
		em.persist(comments1);

		Comments comments2 = new Comments();
		comments2.setComment("comments2");
		em.persist(comments2);

		board.addComment(comments1);
		board.addComment(comments2);

		// 2
		Board board2 = new Board();
		board2.setBoardName("boardName1");
		em.persist(board2);

		Comments comments3 = new Comments();
		comments1.setComment("comments3");
		em.persist(comments3);

		Comments comments4 = new Comments();
		comments2.setComment("comments4");
		em.persist(comments4);

		board2.addComment(comments3);
		board2.addComment(comments4);

		tx.commit();
		em.close();
	}

	@Test
	public void getBoardData() {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		// 1
		Board board = new Board();
		board.setBoardName("boardName1");
		em.persist(board);

		Comments comments1 = new Comments();
		comments1.setComment("comments1");
		em.persist(comments1);

		Comments comments2 = new Comments();
		comments2.setComment("comments2");
		em.persist(comments2);

		board.addComment(comments1);
		board.addComment(comments2);

		// 2
		Board board2 = new Board();
		board2.setBoardName("boardName1");
		em.persist(board2);

		Comments comments3 = new Comments();
		comments1.setComment("comments3");
		em.persist(comments3);

		Comments comments4 = new Comments();
		comments2.setComment("comments4");
		em.persist(comments4);

		board2.addComment(comments3);
		board2.addComment(comments4);

		tx.commit();
		em.close();

		///

		Pageable pageable = PageRequest.of(0, 5);

		Page<BoardDto> result = serviceImpl.findBoardsDto(pageable);

		List<BoardDto> resultList = result.getContent();

		resultList.forEach(
			System.out::println
		);
	}

}