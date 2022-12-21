# Day 6 - 12/19(월)

## 오늘 한 일

```
🎈 save() Method 작업 완료

🎈 save() Method Test 완료

🎈 findIdeal() Method 진행
```

---

## Trouble Shooting

> 아래와 같이 입력 후 회원가입 버튼을 클릭하면

<!-- Image 1 -->

> 분명히 Method 내에서는 이름, 나이가 찍히는데

<!-- Image 2 -->

> DB에는 이름, 나이에 해당하는 부분이 null이 들어간 모습

<!-- Image 3 -->

> Test 역시 성공

<!-- Test Image -->

> 뭐가 문제인지 계속 보다보니 결국 이름, 나이 부분의 Mapping에 문제가 있지 않을까 결론이 났고

```java
/* 수정 전 save() */

SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
jdbcInsert.withTableName("userinfo").usingGeneratedKeyColumns("id");

Map<String, Object> parameter = new HashMap<>();

parameter.put("userId", user.getUserId());
parameter.put("userPw", user.getUserPw());

// Map의 key와 DTO의 field name이 제대로 mapping 되지 않았다.
parameter.put("name", user.getUserName());  // name -> userName
parameter.put("age", user.getUserAge());    // age -> userAge

parameter.put("gender", user.getGender());
parameter.put("phoneNum", user.getPhoneNum());
parameter.put("mbti", user.getMbti());
parameter.put("address", user.getAddress());

Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameter));
user.setId(key.longValue());

return user;

```

```java
/* 수정 후 save() */

SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
jdbcInsert.withTableName("userinfo").usingGeneratedKeyColumns("id");

Map<String, Object> parameter = new HashMap<>();

parameter.put("userId", user.getUserId());
parameter.put("userPw", user.getUserPw());
parameter.put("userName", user.getUserName());
parameter.put("userAge", user.getUserAge());
parameter.put("gender", user.getGender());
parameter.put("phoneNum", user.getPhoneNum());
parameter.put("mbti", user.getMbti());
parameter.put("address", user.getAddress());

Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameter));
user.setId(key.longValue());

return user;

```

> 문제 해결 후 DB

<!-- DB image -->

---

## 기타 사항

> 🎈 Mapping과 같은 간단한 부분에서의 실수로 save method 진행이 더뎌졌다. 더욱 신중을 기해 코딩을 진행해야한다.

> 🎈 checkbox로 받은 값 처리가 익숙하지 않다. 동적 Query에 대한 공부 필요
