package com.gradle.querydsl.domain;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ClassDTO {

	@AllArgsConstructor
	@NoArgsConstructor
	public static class condition {

		private String keyWord;
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

	public static class userFoodVo {

		private String userName;

		private String foodName;
	}
}
