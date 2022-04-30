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

### paging query (deprecated  vs new version)
1. PageImpl<T> 로 반환하는 pagingquery는 deprecated 되었다.
```
@Override
	public PageImpl<ClassDTO.UserFoodVo> pagingList(ClassDTO.condition search) {

		Integer page = search.getPage() - 1; //페이지
		Integer size = search.getSize(); //한번에 보낼 청크 사이즈

		//반환값이 리스트가 아닌 queryResults
		QueryResults<ClassDTO.UserFoodVo> paginglists = jpaQueryFactory
			.select(Projections.constructor(ClassDTO.UserFoodVo.class,
				users.userName,
				food.name))
			.from(food)
			.join(coolTime)
			.on(food.id.eq(coolTime.foodId))
			.join(users)
			.on(users.id.eq(coolTime.userId))
			//페이징 처리 하는 부분
			.offset(page * size)
			.limit(size)
			// --
			.fetchResults(); // fetchResults() -> depreacated 되었음으로 select와 별도로 count 쿼리를 작성

		PageImpl<ClassDTO.UserFoodVo> returnList =
			new PageImpl<>(
				paginglists.getResults(),
				PageRequest.of(page, size),
				paginglists.getTotal());
		return returnList;
	}
```

이를 대시하는 새로운 페이징 쿼리는 다음과 같다.

```
/**
	 * 위의 deprecated 된 메소드 대신 사용하는 문법 (count query를 따로 작성하여 page로 반환)
	 * @param  search, pageable
	 * @return 상위 메소드와 동일한 결과값을 반환함
	 * 반환타입이 list가 아닌 Page임(jpa pagging)
	 */

	@Override
	public Page<ClassDTO.UserFoodVo> paginList2(ClassDTO.condition search, Pageable pageable) {

		List<ClassDTO.UserFoodVo> pagingList = jpaQueryFactory
			// @AllArgsConstructor -> @QueryProjection 붙임
			.select(new QClassDTO_UserFoodVo (
				users.userName,
				food.name
				))
			.from(food)
			.join(coolTime)
			.on(food.id.eq(coolTime.foodId))
			.join(users)
			.on(users.id.eq(coolTime.userId))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();


		JPQLQuery<Long> countQuery = jpaQueryFactory
			.select(users.count())
			.from(users)
			.leftJoin(coolTime)
			.on(coolTime.userId.eq(users.id));

		return PageableExecutionUtils.getPage(pagingList, pageable, countQuery::fetchOne);
	}
```

```
	@DisplayName("deprecated pagin query")
	@Test
	public void deprecatedPaging() {

		ClassDTO.condition condition1 = ClassDTO.condition
			.builder()
			.key("key")
			.size(4)
			.page(1)
			.build();

		ClassDTO.condition condition2 = ClassDTO.condition
			.builder()
			.key("key")
			.size(3)
			.page(1)
			.build();

		PageImpl<ClassDTO.UserFoodVo> lists1 = foodRepository.pagingList(condition1);
		PageImpl<ClassDTO.UserFoodVo> lists2 = foodRepository.pagingList(condition2);

		for (ClassDTO.UserFoodVo lis: lists1) {
			System.out.print(lis.getFoodName() + " " + lis.getUserName());
			System.out.println();
		}

		for (ClassDTO.UserFoodVo lis : lists2) {
			System.out.println(lis.getFoodName() + " " + lis.getUserName());
			System.out.println();
		}

		Assertions.assertThat(lists1.getSize()).isEqualTo(4);
		Assertions.assertThat(lists2.getSize()).isEqualTo(3);
	}
```