package com.gradle.querydsl.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "cooltime")
public class CoolTime {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "food_id")
	private Long foodId;

	@Column(name = "user_id")
	private Long userId;
}
