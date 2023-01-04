package himedia.project.service.board;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import himedia.project.domain.board.Board;
import himedia.project.domain.board.Comment;
import himedia.project.repository.board.BoardRepository;
import himedia.project.repository.board.CommentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {
	
	private final BoardRepository boardRepository;
	private final CommentRepository commentRepository;

	@Override
	public void insertBoard(Board board) {
		boardRepository.save(board);
	}

	@Override
	public List<Board> getBoardList() {
		return boardRepository.findAll();
	}

	@Override
	public Board getBoard(Board board) {
		return boardRepository.findById(board.getBoardIdx()).get();
	}
	
	@Override
	public int hitCnt(Long boardIdx) {
		return boardRepository.updateHitCnt(boardIdx);
	}

	@Override
	public void updateBoard(Board board) {
		boardRepository.save(getBoard(board).update(board));
	}

	@Override
	public void deleteBoard(Board board) {
		boardRepository.deleteById(board.getBoardIdx());
	}
	
	@Override
	public Page<Board> findAllPage(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
	
	// comment
	@Override
	public void insertComment(Comment comment) {
		commentRepository.save(comment);
	}
	
	@Override
	public List<Comment> getComment(Long boardIdx) {
		return commentRepository.findCommentById(boardIdx);
	}
	
}
