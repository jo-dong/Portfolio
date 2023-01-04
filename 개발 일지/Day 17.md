# Day 17 - 01/05(목)

## 오늘 한 일

```
🎈 댓글 기능 작업 완료
```

---

## 진행 상황

> BoardController

```java
	// 게시글 상세
	@GetMapping("/{boardIdx}")
	public String board(@PathVariable(value="boardIdx", required = false) Long boardIdx,
			    @ModelAttribute Board board,
			    Model model) {

		Board boardDetail = boardService.getBoard(board);

		// 조회수 1 증가
		boardService.hitCnt(boardIdx);

		log.info("getComment 실행 시작");

		// 댓글 목록 불러오기
		List<Comment> commentList = boardService.getComment(boardIdx);

		log.info("getComment 실행 완료");

		model.addAttribute("boardDetail", boardDetail);
		model.addAttribute("comment", commentList);
		return "/board/board-detail";
	}


	// comment
	@PostMapping("/{boardIdx}")
	public String comment(@PathVariable(name = "boardIdx", required = false) Long boardIdx,
			      Comment comment) {
		log.info("insertComment 실행 시작");
		log.info("boardIdx -> {}", boardIdx);
		log.info("memberName -> {}", comment.getMemberName());
		log.info("content -> {}", comment.getContent());
		log.info("createDate -> {}", comment.getCreateDate());

		boardService.insertComment(comment);

		log.info("insertComment 실행 완료");
		return "redirect:/board/"+boardIdx;
	}
```

> BoardServiceImpl

```java
	// comment
	@Override
	public void insertComment(Comment comment) {
		commentRepository.save(comment);
	}

	@Override
	public List<Comment> getComment(Long boardIdx) {
		log.info("boardIdx -> {}", boardIdx);
		return commentRepository.findCommentById(boardIdx);
	}
```

> CommentRepository

```java
public interface CommentRepository extends JpaRepository<Comment, Long> {

	@Transactional
	@Modifying
	@Query("select c from Comment c where c.boardIdx = :boardIdx order by c.commentIdx")
	public List<Comment> findCommentById(@Param("boardIdx") Long boardIdx);
}
```

> board-detail.html

```html
<!-- Comment -->
<h5>댓글을 남기세요</h5>

<div>
  <table>
    <tr>
      <th>작성자</th>
      <th>내용</th>
      <th>작성 시간</th>
    </tr>
    <tr th:each="comment:${comment}">
      <td th:text="${comment.memberName}"></td>
      <td th:text="${comment.content}"></td>
      <td th:text="${comment.createDate}"></td>
    </tr>
  </table>
</div>

<div>
  <form th:action="@{|/board/${board.boardIdx}|}" method="post">
    <input type="hidden" name="boardIdx" th:value="*{board.boardIdx}" />
    <fieldset>
      <dl>
        <dt>
          <label for="memberName">작성자 : </label>
        </dt>
        <dd>
          <input type="text" name="memberName" />
        </dd>
        <dt>
          <label for="content">댓글 :</label>
        </dt>
        <dd>
          <input type="text" name="content" />
        </dd>
      </dl>
      <input type="submit" value="작성" />
    </fieldset>
  </form>
</div>
```

## Trouble Shooting

> 📌 이전 Controller : @ModelAttribute Comment comment
>
> > -> Error : Field 'board_idx' doesn't have a default value

```java
	// comment
	@PostMapping("/{boardIdx}")
	public String comment(@PathVariable(name = "boardIdx", required = false) Long boardIdx,
			      @ModelAttribute Comment comment) {    // @ModelAttribute Annotation을 사용했더니 binding이 되질 않고
                                                                    // 계속 log를 찍어봐도 null이 출력되었다.
		log.info("insertComment 실행 시작");
		log.info("boardIdx -> {}", boardIdx);
		log.info("memberName -> {}", comment.getMemberName());  // null
		log.info("content -> {}", comment.getContent());        // null
		log.info("createDate -> {}", comment.getCreateDate());  // null

		boardService.insertComment(comment);

		log.info("insertComment 실행 완료");
		return "redirect:/board/"+boardIdx;
	}
```

> 📌 @ModelAttribute 제거
>
> > binding 잘 됨

<!-- Image 1 -->
![0105_1](https://user-images.githubusercontent.com/111822816/210561733-de942c9e-bf93-45a0-90a2-ab372df9e800.png)

> 댓글 수 기능 추가하기
