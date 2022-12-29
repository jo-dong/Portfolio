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
@Table(name = "comment")
public class Comment extends BaseTime {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_idx")
	private Long commentIdx;
	
	@Lob @Column(nullable = false)
	private String content;
	
	@Column(updatable = false)
	@ColumnDefault("0")
	private Long likes;
	
	@Column(updatable = false)
	@ColumnDefault("0")
	private Long dislikes;
	
	@Builder
	public Comment(Long commentIdx, String content) {
		this.commentIdx = commentIdx;
		this.content = content;
	}
	
	/**
	 * Assert.notNull
	 * : 값이 null이면 예외를 발생시키는 Method
	 * : 방어 코드의 역할
	 * : 테스트 실행 시 어디에서 문제가 발생했는지 쉽게 찾을 수 있음
	 */
	public Comment update(Comment comment) {
		Assert.notNull(content, "내용은 필수 입력 항목입니다.");
		this.content = comment.content;
		return this;
	}
}
