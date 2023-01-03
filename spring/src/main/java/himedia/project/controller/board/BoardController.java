package himedia.project.controller.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import himedia.project.domain.board.Board;
import himedia.project.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {
	
//	private final MemberService memberService;
	private final BoardService boardService;
	
	// 게시판 목록
	@GetMapping
	public String boardList(@PageableDefault(page=0, size=10) Pageable pageable,
							@RequestParam(value = "page", required = false) Integer page,
							Model model) {
		Page<Board> list = boardService.findAllPage(pageable);
		
		// pageable은 0부터 시작 -> 1을 처리하려면 +1
		int currentPage = list.getPageable().getPageNumber() + 1;
		
		// 제일 앞페이지 : -1이 들어가지 않도록 max -> 두 수 비교 후 대입
		int startPage = Math.max(currentPage - 4, 1);
		int endPage = Math.min(currentPage + 9, list.getTotalPages());
		
		model.addAttribute("list", list);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
//		model.addAttribute("boardList", boardService.getBoardList());
		return "/board/list";
	}
	
	// 게시글 상세
	@GetMapping("/{boardIdx}")
	public String board(@PathVariable(value="boardIdx", required = false) Long boardIdx,
						@ModelAttribute Board board,
						Model model) {
		
		Board boardDetail = boardService.getBoard(board);
		
		// 조회수 1 증가
		boardService.hitCnt(boardIdx);
		
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
	public String updateBoard(@PathVariable(value="boardIdx", required=false) Long boardIdx, 
			 				  @ModelAttribute Board board,
							  Model model) {
		Board boardDetail = boardService.getBoard(board);		
		model.addAttribute("board", boardDetail);
		return "/board/update";
	}
	
	@PostMapping("/update/{boardIdx}")
	public String update(@PathVariable(value="boardIdx", required=false) Long boardIdx,
						 @ModelAttribute Board board) {
		log.info("boardIdx = {}", boardIdx);
		log.info("content = {}", board.getContent());
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
