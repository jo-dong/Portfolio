# Day 3 - 12/14(수)

## 오늘 한 일

```
- 주제 선정
    - 주제 : 결혼 정보 회사
    - 회원 가입 후 이상형 검색과 오프라인 상담 예약 등을 제공하는 웹 사이트 제작

- 참고 사이트 검색
    - 여러 결혼 정보 회사 사이트를 돌아보며 구조 탐색

- 화면 설계서 작업
    - 서비스 개요, 요구사항 정의 추가, 전체적인 목차 정리
```

### - userDTO 추가 : 수정 필요

```java
@AllArgsConstructor
@NoArgsConstructor
@Entity @Getter @Setter
public class UserDTO {

	// user id
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private Long userId;

	// user name
	@Column(name = "userName")
	private String userName;

	// user age
	@Column(name = "userAge")
	private Integer userAge;

	// gender
	@Column(name = "gender")
	private String gender;

	// user phone number
	@Column(name = "phoneNum")
	private String phoneNum;

	// user MBTI
	@Column(name = "mbti")
	private String mbti;

	// user address
	@Column(name = "address")
	private String address;
}
```

---

## 추가 사항

![Alt text](markdown_practice.png)

> 개발일지 작성에 용이하도록 MarkDown 언어에 대해 추가적으로 연습을 하였다.
