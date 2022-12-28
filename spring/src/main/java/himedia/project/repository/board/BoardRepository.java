package himedia.project.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;

import himedia.project.domain.board.Board;

/**
 * 게시판 Repository
 */
public interface BoardRepository extends JpaRepository<Board, Long> {

}
