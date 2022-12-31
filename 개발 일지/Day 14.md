# Day 14 - 12/31(토)

## 오늘 한 일

```
🎈 게시판 조회수 증가 method 작성

🎈 Delete 기능 추가
```

---

## 진행 상황

> 조회수 증가 Method(BoardRepository)

```java
/**
 * 게시판 Repository
 */
public interface BoardRepository extends JpaRepository<Board, Long> {
	@Transactional
	@Modifying
	@Query("Update Board b Set b.hitCnt = b.hitCnt + 1 Where b.boardIdx = :boardIdx")
	public int updateHitCnt(@Param("boardIdx") Long boardIdx);
}
```

> 조회수 증가 Method(BoardServiceImpl)

```java
@Override
public int hitCnt(Long boardIdx) {
	return boardRepository.updateHitCnt(boardIdx);
}
```

> 조회수 증가 Method(BoardController)

```java
// 게시글 상세
@GetMapping("/{boardIdx}")
public String board(@PathVariable(value="boardIdx", required = false) Long boardIdx,
					@ModelAttribute Board board,
					Model model) {

    // board 불러오기
	Board boardDetail = boardService.getBoard(board);

	// 조회수 1 증가
	boardService.hitCnt(boardIdx);

	model.addAttribute("boardDetail", boardDetail);
	return "/board/board-detail";
	}
```

---

> Delete 기능(html - /board/board-detail)
>
> > @DeleteMapping은 method에 바로 delete를 사용하면 인식하지 못한다.

```html
<form th:action="@{|/board/delete/${board.boardIdx}|}" method="post">
  <input type="hidden" name="_method" value="delete" />
  <input type="submit" value="게시글 삭제" />
</form>

<!-- [application.properties]에 추가  -->
spring.mvc.hiddenmethod.filter.enabled=true
```

> 게시글 삭제 Method(BoardServiceImpl)

```java
@Override
public void deleteBoard(Board board) {
	boardRepository.deleteById(board.getBoardIdx());
}
```

> 게시글 삭제 Method(BoardController)

```java
// delete
@DeleteMapping("/delete/{boardIdx}")
public String delete(Board board) {
	boardService.deleteBoard(board);
	return "redirect:/board";
}
```

> 게시글 삭제 전

<!-- Image 3 -->
![1231_3](https://user-images.githubusercontent.com/111822816/210129364-d58ae6fd-bfb1-493b-8464-a6d2831aa84e.png)

> 게시글 삭제 후

<!-- Image 4 -->
![1231_4](https://user-images.githubusercontent.com/111822816/210129366-d82cf3a0-ec12-4f14-8efb-773bf34fd9ea.png)

---

## Trouble Shooting

### 📌 문제 발생

> 조회수 증가 code 작성 후 실행

```java
[Error 발생]
org.hibernate.hql.internal.QueryExecutionRequestException:
Not supported for DML operations [Update himedia.project.domain.board.Board b Set b.hitCnt = b.hitCnt + 1 Where b.boardIdx = :boardIdx]
```

---

### 📌 문제 해결

> updateHitCnt() method 위에 @Modifying Annotation을 추가해주었다.
>
>       DML(Insert, Update, Delete)을 사용할 때에는 반드시 @Modifying Annotation 사용할 것
>
> 출처 : https://joojimin.tistory.com/71

---

### 📌 문제 해결 후

> 조회수 : 52

<!-- Image 1 -->
![1231_1](https://user-images.githubusercontent.com/111822816/210129355-3660f693-e02a-4c04-b2d5-8c3b3d19a4b1.png)

> 조회수 : 53

<!-- Image 2 -->
![1231_2](https://user-images.githubusercontent.com/111822816/210129358-cc2fae74-c1d9-4b26-92e1-91ed12eb1cfb.png)

---

## 기타 사항

```
앞으로 할 일

🎈 delete에 Alert Message(정말 삭제하시겠습니까?) 등을 추가 - 아마도 JavaScript

🎈 Ideal Table/Entity : 체크박스로 선택한 항목이 모두 저장되어야 하기 때문에 Entity 자체를 List로 만들어야 하는 것인지 / 어떻게 구현해야 하는지 고민 후 작업
```
