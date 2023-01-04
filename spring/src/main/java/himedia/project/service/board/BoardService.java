package himedia.project.service.board;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import himedia.project.domain.board.Board;
import himedia.project.domain.board.Comment;

public interface BoardService {
	// board
	public void insertBoard(Board board);
	public List<Board> getBoardList();
	public Board getBoard(Board board);
	public int hitCnt(Long boardIdx);
	public void updateBoard(Board board);
	public void deleteBoard(Board board);
	public Page<Board> findAllPage(Pageable pageable);
	
	// comment
	public void insertComment(Comment comment);
	public List<Comment> getComment(Long boardIdx);
}
