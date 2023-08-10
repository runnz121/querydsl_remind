package com.gradle.querydsl.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.DerbyTemplates;
import com.querydsl.sql.MySQLTemplates;
import com.querydsl.sql.SQLTemplates;

@Configuration
public class Config {

	@PersistenceContext
	private EntityManager entityManager;

	@Bean
	public JPAQueryFactory jpaQueryFactory() {
		return new JPAQueryFactory(entityManager);
	}

	@Bean
	public JPASQLQuery jpasqlQuery() {
		SQLTemplates sqlTemplates = MySQLTemplates.builder().build();
		return new JPASQLQuery(entityManager, sqlTemplates);
	}

}
