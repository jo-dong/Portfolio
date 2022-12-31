package himedia.project.service.board;

import java.util.List;

import himedia.project.domain.board.Board;

public interface BoardService {
	void insertBoard(Board board);
	List<Board> getBoardList();
	Board getBoard(Board board);
	public int hitCnt(Long boardIdx);
	void updateBoard(Board board);
	void deleteBoard(Board board);
}
