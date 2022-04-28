package com.gradle.querydsl.domain;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;

import com.querydsl.core.Tuple;
import com.querydsl.core.annotations.QueryProjection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ClassDTO {

	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	public static class condition {

		private String keyWord;

		private Integer page;

		private Integer size;
	}

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class UserVO {

		private String userName;

		private String email;

		public static UserVO of(Users users) {
			return new UserVO(users.getUserName(), users.getEmail());
		}
	}

	@Getter
	@NoArgsConstructor
	public static class UserFoodVo {

		private String userName;

		private String foodName;

		//Q class dto 만들기 위해 선언(@AllargsConstrouctor)
		@QueryProjection
		public UserFoodVo (String userName, String foodName) {
			this.userName = userName;
			this.foodName = foodName;
		}

	}

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class threeJoin {

		private String userName;

		private String foodName;

		private Long coolTimeId;

	}
}
