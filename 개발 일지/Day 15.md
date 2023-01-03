# Day 15 - 01/03(화)

## 오늘 한 일

```
🎈 게시판 Paging 처리

🎈 게시판 댓글 기능 일부 작업
```

---

## 진행 상황

> 게시판 Paging 구현

<!-- Image 1 -->

> Controller
>
> > 기존 model을 지우고 Spring에서 지원하는 Pageable Interface를 사용하여 게시판 목록 Paging 처리

```java
	@GetMapping
	public String boardList(@PageableDefault(page=0, size=10) Pageable pageable,
							@RequestParam(value = "page", required = false) Integer page,
							Model model) {
		Page<Board> list = boardService.findAllPage(pageable);

		int currentPage = list.getPageable().getPageNumber() + 1;

		int startPage = Math.max(currentPage - 4, 1);
		int endPage = Math.min(currentPage + 9, list.getTotalPages());

		model.addAttribute("list", list);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

//		model.addAttribute("boardList", boardService.getBoardList());
		return "/board/list";
	}
```

> Service

```java
	@Override
	public Page<Board> findAllPage(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
```

> Thymeleaf

```html
<th:block th:each="page:${#numbers.sequence(startPage,endPage)}">
  <a
    th:if="${page != currentPage}"
    th:href="@{/board(page=${page-1})}"
    th:text="${page}"
  ></a>
  <strong
    th:if="${page==currentPage}"
    th:text="${page}"
    style="color: red"
  ></strong>
</th:block>
```

> 댓글 기능 약식으로 view 작성

<!-- Image 2 -->

---

## 기타 사항

```
🎈 앞으로 할 일 : 댓글 기능 / 회원 통계 기능

🎈 시간 남으면 로그인/게시판 연동(시간 없으면 우선 배제)

🎈 발표 자료(ppt) 금요일까지
```
