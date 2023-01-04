package himedia.project.repository.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import himedia.project.domain.board.Comment;

/**
 * 댓글 Repository
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	@Transactional
//	@Modifying
	@Query("select c from Comment c where c.boardIdx = :boardIdx order by c.commentIdx")
	public List<Comment> findCommentById(@Param("boardIdx") Long boardIdx);
}
