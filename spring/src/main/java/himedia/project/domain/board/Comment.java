package himedia.project.domain.board;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.Assert;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends BaseTime {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "comment_idx")
	private Long commentIdx;
	
	@Column(name = "board_idx")
	private Long boardIdx;
	
	@Column(name = "member_name")
	private String memberName;
	
	@Lob @Column(nullable = false)
	private String content;
	
	@Builder
	public Comment(Long commentIdx, String content) {
		this.commentIdx = commentIdx;
		this.content = content;
	}
	
	public Comment update(Comment comment) {
		Assert.notNull(content, "내용은 필수 입력 항목입니다.");
		this.content = comment.content;
		return this;
	}
}
