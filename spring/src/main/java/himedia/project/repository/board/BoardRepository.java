package himedia.project.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import himedia.project.domain.board.Board;

/**
 * 게시판 Repository
 */
public interface BoardRepository extends JpaRepository<Board, Long> {
	@Transactional
	@Modifying
	@Query("Update Board b Set b.hitCnt = b.hitCnt + 1 Where b.boardIdx = :boardIdx")
	public int updateHitCnt(@Param("boardIdx") Long boardIdx);
}
