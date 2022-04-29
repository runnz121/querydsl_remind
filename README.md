### 1. 설정
```
@Configuration
public class Config {

	@PersistenceContext
	private EntityManager entityManager;

	@Bean
	public JPAQueryFactory jpaQueryFactory() {
		return new JPAQueryFactory(entityManager);
	}
```

Config 파일 만들고 jpaQueryFacotry bean 등록


application.yml
```

  jpa:
    properties:
      hibernate:
        format_sql: true
        use_sql_comments : true

logging.level:
  org.hibernate.SQL: debug
```
jpa properties show sql 처리하면 자동으로 구현해줌 


### 2, repository 구성

총 3개의 리포가 구성된다

JpaRepository를 상속받는 interface 리포지토리

CustomReppository interface

CustomRepository interface 구현하는 구현체 CustomerRepositoryImpl

```
@Repository
@RequiredArgsConstructor
public class FoodRepositoryImpl implements FoodRepositoryCustom{

	private final JPAQueryFactory jpaQueryFactory;


	@Override
	public List<ClassDTO.UserFoodVo> foodList() {
		List<ClassDTO.UserFoodVo> lists =  jpaQueryFactory.
			select(Projections.constructor(ClassDTO.UserFoodVo.class, users.userName, food.name))
			.from(food)
			.join(coolTime)
			.on(food.id.eq(coolTime.foodId))
			.join(users)
			.on(users.id.eq(coolTime.userId))
			.fetch()
			;

		return lists;

	}
}
```

기본형 JPAQueryFactory 주입 받음, RequriedArgsConstrucotr를 통해 생성자 주입
join, on 으로 설정

