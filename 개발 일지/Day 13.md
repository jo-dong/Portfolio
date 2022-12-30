# Day 13 - 12/30(금)

## 오늘 한 일

```
🎈 board controller 수정

🎈 /board/write 페이지 추가

🎈 /board/update 페이지 추가
```

---

## 진행 상황

> Board Controller

```java
package himedia.project.controller.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import himedia.project.domain.board.Board;
import himedia.project.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {

	private final BoardService boardService;

	// 게시판 목록
	@GetMapping()
	public String boardList(Model model) {
		model.addAttribute("boardList", boardService.getBoardList());
		return "/board/list";
	}

	// 게시글 상세
	@GetMapping("/{boardIdx}")
	public String board(@PathVariable(value="boardIdx", required = false) Long boardIdx, Model model) {
		Board board = new Board();
		board.setBoardIdx(boardIdx);
		Board boardDetail = boardService.getBoard(board);

		log.info("boardDetail -> {}", boardDetail.getBoardIdx());
		log.info("boardDetail -> {}", boardDetail.getTitle());
		log.info("boardDetail -> {}", boardDetail.getContent());

		model.addAttribute("boardDetail", boardDetail);
		return "/board/board-detail";
	}

	// insert
	@GetMapping("/write")
	public String insertBoard() {
		return "/board/write";
	}

	@PostMapping("/write")
	public String write(Board board) {
		boardService.insertBoard(board);
		return "redirect:/board";
	}

	// update
	@GetMapping("/update/{boardIdx}")
	public String updateBoard(@PathVariable(value="boardIdx", required=false) Long boardIdx, Model model) {
		Board board = new Board();
		board.setBoardIdx(boardIdx);
		Board boardDetail = boardService.getBoard(board);
		model.addAttribute("board", boardDetail);
		return "/board/update";
	}

	@PostMapping("/update/{boardIdx}")
	public String update(@PathVariable Long boardIdx) {
		Board board = new Board();
		board.setBoardIdx(boardIdx);
		boardService.updateBoard(board);
		return "redirect:/board/" + boardIdx;
	}

	// delete
	@DeleteMapping("/delete/{boardIdx}")
	public String delete(Board board) {
		boardService.deleteBoard(board);
		return "redirect:/board";
	}

}

```

> /board/update/{boardIdx}

<!-- Image 4 -->

---

## Trouble Shooting

### 📌 문제 발생

> ⓐ return의 주소에 "redirect:/board/" + boardIdx; 를 했는데 아래와 같이 주소가 발생

<!-- Image 3 -->

> ⓑ 처음 입력한 form th:action

<!-- Image 2 -->

> ⓒ 처음 입력한 update Controller

```java
@PostMapping("/update/{boardIdx}")
public String update(@PathVariable Long boardIdx) {
    Board board = new Board();
    board.setBoardIdx(boardIdx);
    boardService.updateBoard(board);
    log.info("boardIdx = {}", boardIdx);
    return "redirect:/board/" + boardIdx;
}
```

---

### 📌 문제 해결

> ⓐ log를 찍어서 boardIdx 값 확인 -> %7BboardIdx%7d
>
> form 태그의 action 값 변경
>
>       th:action="@{|/board/update/{boardIdx}(boardIdx=${board.boardIdx})|}"
>       ->
>       th:action="@{|/board/update/${board.boardIdx}|}"
>
>       => log.info(boardIdx+"") = 2>

> ⓑ 링크는 해결되었으나 board-detail 페이지에 content 내용이 출력되지 않음
>
> -> textarea 태그를 input type="text"로 변경 // 해결

> ⓒ 수정완료 버튼을 클릭했을 때 Controller에서 board.content를 null로 받아들임
>
> -> Controller Code 변경

```java
    @PostMapping("/update/{boardIdx}")
	public String update(@PathVariable(value="boardIdx", required=false) Long boardIdx,
						 @ModelAttribute Board board) {
		log.info("boardIdx = {}", boardIdx);
		log.info("content = {}", board.getContent());
		boardService.updateBoard(board);
		return "redirect:/board/" + boardIdx;
	}
```

> 🎈 전 code에서 빈 객체를 만들어서 update() method를 돌리다보니 null로 입력이 되었던 것.

---

### 📌 문제 해결 후

> /board/2 -> board-detail page로 잘 수정되어 보여주는 모습

<!-- Image 5 -->

> DB에 수정된 내용과 update_date가 잘 변경된 모습

<!-- Image 6 -->

---

## 기타 사항

```
앞으로 할 일

🎈 Board - Delete 기능 구현하기

🎈 Ideal - 이상형 찾기(checkBox) 기능 구현하기
```
