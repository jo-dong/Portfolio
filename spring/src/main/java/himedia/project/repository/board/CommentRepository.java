package himedia.project.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;

import himedia.project.domain.board.Comment;

/**
 * 댓글 Repository
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
