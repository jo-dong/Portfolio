package himedia.project.domain.board;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.Assert;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Table(name = "board")
public class Board extends BaseTime {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_idx")
	private Long boardIdx;
	
	@Column(length = 250, nullable = false)
	private String title;
	
	@Lob @Column(nullable = false)
	private String content;
	
	// 로그인 해결 후 적용
//	private String memberName;
	
	@Column(updatable = false)
	@ColumnDefault("0")
	private Long likes;
	
	@Column(updatable = false)
	@ColumnDefault("0")
	private Long dislikes;
	
	@Column(updatable = false, name = "hit_cnt")
	@ColumnDefault("0")
	private Long hitCnt;
	
	@Builder
	public Board(Long boardIdx, String title, String content) {
		this.boardIdx = boardIdx;
		this.title = title;
		this.content = content;
	}
	
	/**
	 * Assert.notNull
	 * : 값이 null이면 예외를 발생시키는 Method
	 * : 방어 코드의 역할
	 * : 테스트 실행 시 어디에서 문제가 발생했는지 쉽게 찾을 수 있음
	 */
	public Board update(Board board) {
		Assert.notNull(title, "제목은 필수 입력 항목입니다.");
		Assert.notNull(content, "내용은 필수 입력 항목입니다.");
		
		title = board.title;
		content = board.content;
		
		return this;
	}
}
