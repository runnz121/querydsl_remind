package com.gradle.querydsl.repository.Users;

import java.util.List;

import org.apache.catalina.User;

import com.gradle.querydsl.domain.ClassDTO;
import com.gradle.querydsl.domain.Users;

public interface UsersRepositoryCustom {

	List<Users> findUse();

	List<ClassDTO.threeJoin> finds();

}
