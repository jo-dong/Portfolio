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
import lombok.Setter;

/* 
 * [board Table create query]

create table board (
	board_idx bigint NOT NULL auto_increment,
    title varchar(255) NOT NULL,
    content longtext NOT NULL,
  # 로그인 해결 후 member_name 추가
    likes int default 0,
    dislikes int default 0,    
    create_date datetime,
    update_date datetime,
    hit_cnt int default 0,
    primary key(board_idx)
);
 */

@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Table(name = "board")
public class Board extends BaseTime {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_idx", nullable = false)
	private Long boardIdx;
	
	@Column(name = "member_name")
	private String memberName;
	
	@Column(length = 250, nullable = false)
	private String title;
	
	@Lob @Column(nullable = false)
	private String content;
	
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
