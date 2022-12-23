# Day 9 - 12/22(목)

## 오늘 한 일

```
🎈 이상형 검색 기능 - 간단하게 View 제작

🎈 checkbox로 받은 값 처리 연구

🎈 Controller/Service/Repository : 이상형 검색 기능 작성 방법 연구
```

---

## 진행 상황

> 이상형 검색 기능 view

<!-- image -->
![view_ideal_check](https://user-images.githubusercontent.com/111822816/209100060-b3cbcaf1-af20-4094-bd36-72025cacd7e3.png)

> controller

```java
    @PostMapping("/ideal/check")
	public String check(@RequestParam(value = "age", required = false) String age,
						@RequestParam(value = "residence", required = false) String residence,
						@RequestParam(value = "mbti", required = false) String mbti) {
		service.search(age, residence, mbti);
		return "/ideal/result";
	}

  String age로 한 이유 : view의 checkbox에서 'value' 속성으로 넘어오는 값이
  문자열인것 같아 우선 String으로 설정.
  맞는지 아닌지는 Test 해봐야 알 것 같음...
```

> Service

```java
    // 이상형 검색
	public void search(String age, String residence, String mbti) {
		idealRepository.findByAge(age);
		idealRepository.findByAddress(residence);
		idealRepository.findByMbti(mbti);
	}

  Service에서 Repository의 각 Method로 뿌려줌
```

> Repository(미완)

```java
    // 이상형 검색
	public Optional<UserDTO> findByAge(String age) {
		String str = "";
		em.createQuery(str, UserDTO.class);
		return Optional.empty();
	}

	public Optional<UserDTO> findByAddress(String residence) {

		return Optional.empty();
	}

	public Optional<UserDTO> findByMbti(String mbti) {

		return Optional.empty();
	}

  아직 Query문을 어떻게 짜야할지 모르겠어서 완성하지 못함.
  MySQL과 연동 후 작성할 지도 고민 중에 있음.
```

---

## 기타 사항

> CheckBox로부터 넘어온 값을 조건문으로 처리하는 방법, Query문 완성이 시급.

> MySQL과의 연결도 생각 중
