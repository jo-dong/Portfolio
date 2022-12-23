# Day 6 - 12/19(월)

## 오늘 한 일

```
🎈 JDBC Template을 이용하여 Repository 구성

🎈 view : sign-up page 구현, 테스트
```

## Code

```java
🎈 Repository(회원가입) - save() Method 구현

@Repository
@RequiredArgsConstructor
public class UserRegisterRepository implements MarryRepository {

	private JdbcTemplate jdbcTemplate;

	private RowMapper<UserDTO> userRowMapper() {
		return (rs, rowNum) -> {
			UserDTO user = new UserDTO();
			user.setId(rs.getLong("id"));
			user.setUserId(rs.getString("userId"));
			user.setUserPw(rs.getString("userPw"));
			user.setUserName(rs.getString("userName"));
			user.setUserAge(rs.getInt("userAge"));
			user.setGender(rs.getString("gender"));
			user.setPhoneNum(rs.getString("phoneNum"));
			user.setMbti(rs.getString("mbti"));
			user.setAddress(rs.getString("address"));
			return user;
		};
	}

	@Override
	public UserDTO save(UserDTO user) {
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		jdbcInsert.withTableName("user").usingGeneratedKeyColumns("id");

		Map<String, Object> parameter = new HashMap<>();

		parameter.put("userId", user.getUserId());
		parameter.put("userPw", user.getUserPw());
		parameter.put("name", user.getUserName());
		parameter.put("age", user.getUserAge());
		parameter.put("gender", user.getGender());
		parameter.put("phoneNum", user.getPhoneNum());
		parameter.put("mbti", user.getMbti());
		parameter.put("address", user.getAddress());

		Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameter));
		user.setId(key.longValue());

		return user;
	}

}
```

---

## View

<!-- image -->
![sign-up](https://user-images.githubusercontent.com/111822816/208379146-b91a7d0b-06a2-45cd-930c-1b16fa6bb103.png)

---

## 기타 사항

> 🎈 기능별로 추가적인 Domain 설계가 필요해 보임

> 🎈 지금의 작업은 User 관리에 관한 작업 / 하다보니 손이 많이 간다...
