package himedia.project.controller.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import himedia.project.domain.board.Board;
import himedia.project.service.board.BoardService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping()
	public String boardList(Model model) {
		model.addAttribute("boardList", boardService.getBoardList());
		return "/board/list";
	}
	
	@GetMapping("/{boardIdx}")
	public String board(Board board, Model model) {
		model.addAttribute("board", boardService.getBoard(board));
		return "/board/board-detail";
	}
	
	@GetMapping("/write")
	public String insertBoard() {
		return "/board/write";
	}
	
	/**
	 * QueryString만 다르게 하면서 요청을 분산시킬 때
	 * : params attribute 사용
	 */
	@GetMapping("/update/{boardIdx}")
	public String updateBoard(Board board, Model model) {
		model.addAttribute("board", boardService.getBoard(board));
		return "/board/board-write";
	}
	
	@PostMapping
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
