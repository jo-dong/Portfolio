# Day 12 - 12/29(목)

## 오늘 한 일

```java
🎈 Board View 작성 / Board Controller 작성

🎈 Board Entity 수정(@Setter 추가)

🎈 Login/Logout 추가
```

---

## 진행 상황

> 🎈 Board View

> > /board  : 이모지도 저장 가능

<!-- Image1 -->
![board1](https://user-images.githubusercontent.com/111822816/209929570-d548b7b2-bfe0-4e63-a116-12e56cacbd1f.png)

> > /board/{boardIdx}

<!-- Image2 -->
![board2](https://user-images.githubusercontent.com/111822816/209929582-5637c6db-8146-4c83-b666-1016b09e498e.png)

> > /board/write

<!-- Image3 -->
![board3](https://user-images.githubusercontent.com/111822816/209929603-faea12dc-f609-49d8-8103-8cb1829b82f2.png)

> 🎈 Board Controller (update/delete 아직 미완)

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

	@GetMapping()
	public String boardList(Model model) {
		model.addAttribute("boardList", boardService.getBoardList());
		return "/board/list";
	}

	@GetMapping("/{boardIdx}")
	public String board(@PathVariable(value="boardIdx", required = false) Long boardIdx,
						Model model) {
		Board board = new Board();
		board.setBoardIdx(boardIdx);
		Board boardDetail = boardService.getBoard(board);

		log.info("boardDetail -> {}", boardDetail.getBoardIdx());
		log.info("boardDetail -> {}", boardDetail.getTitle());
		log.info("boardDetail -> {}", boardDetail.getContent());

		model.addAttribute("boardDetail", boardDetail);
		return "/board/board-detail";
	}

	@GetMapping("/write")
	public String insertBoard() {
		return "/board/write";
	}

	@GetMapping("/update/{boardIdx}")
	public String updateBoard(Board board, Model model) {
		model.addAttribute("board", boardService.getBoard(board));
		return "/board/board-write";
	}

	@PostMapping("/write")
	public String write(Board board) {
		boardService.insertBoard(board);
		return "redirect:/board";
	}

	@PostMapping("/update/{boardIdx}")
	public String update(Board board) {
		boardService.updateBoard(board);
		return "redirect:/board/" + board.getBoardIdx();
	}

	@DeleteMapping("/delete/{boardIdx}")
	public String delete(Board board) {
		boardService.deleteBoard(board);
		return "redirect:/board";
	}

}
```

> 🎈 Login/Logout

> > 로그인을 하면

<!-- Image1 -->
![login1](https://user-images.githubusercontent.com/111822816/209929615-ed4ecfac-d871-4189-a966-d5fcca552e26.png)

> > member_name을 화면에 출력

<!-- Image2 -->
![login2](https://user-images.githubusercontent.com/111822816/209929624-aa62cfdc-2803-49da-8b9f-de70c8165e59.png)

> > Session/Cookie에도 저장

<!-- Image3 -->
![login3](https://user-images.githubusercontent.com/111822816/209929633-872a77bb-fe1a-43dc-bbc2-60d979919701.png)

---

## 기타 사항

```
🎈 로그인 시 Session/Cookie에 저장되는 것 같긴 한데 저걸 어떻게 다시 꺼내서 써야할 지 감이 오지 않는다.

🎈 로그인 성공 시에는 문제가 없지만 일치하지 않는 정보를 입력하거나, 아무 것도 입력하지 않을 때 에러 발생. 우선 이번 주엔 로그인 개발 중단!!

🎈 이번 주 내로 게시판 & 다른 기능들의 BackEnd 부분을 모두 완성해야 될 것 같다..
```
