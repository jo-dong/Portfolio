# Day 11 - 12/28(수)

## 오늘 한 일

```
🎈 게시판 DB 생성(게시판, 댓글)

🎈 Repository : JPA -> Spring Data JPA로 변경

🎈 Board Entity, Service 작성
```

---

## 진행 상황

> 🎈 게시판, 댓글 Table 생성

```sql
-- 게시판 Table --
create table board (
	board_idx bigint NOT NULL auto_increment,
    title varchar(255) NOT NULL,
    content longtext NOT NULL,
 -- 로그인 해결 후 member_name 추가
    likes int default 0,
    dislikes int default 0,
    hit_cnt int default 0,
    create_date datetime,
    update_date datetime,
    primary key(board_idx)
);

-- 댓글 Table --
create table comment (
	comment_idx bigint NOT NULL auto_increment,
    board_idx bigint NOT NULL,
    content longtext NOT NULL,
    likes int default 0,
    dislikes int default 0,
 -- 로그인 해결 후 member_name(FK) 추가
	create_date datetime,
    update_date datetime,
    primary key(comment_idx),
    foreign key(board_idx) references board(board_idx)
    on delete cascade
);
```

> 🎈 Repository : JPA -> Spring Data JPA

```java
package himedia.project.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;

import himedia.project.domain.board.Board;

/**
 * Board Repository
 */
public interface BoardRepository extends JpaRepository<Board, Long> {

}
```

```java
package himedia.project.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;

import himedia.project.domain.member.Member;

/**
 * Member Repository
 */
public interface MemberRepository extends JpaRepository<Member, Long>{

}

```

> 🎈 Board Entity / Service 작성

```java
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

	@Column(nullable = false)
	private String title;

  /**
   *  @Lob
   *  : 일반적인 데이터베이스에서 저장하는
   *    길이인 255개 이상의 문자를 저장하고 싶을
   *    때 지정한다.
   */
	@Lob @Column(nullable = false)
	private String content;

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

```

```java
package himedia.project.service.board;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import himedia.project.domain.board.Board;
import himedia.project.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

	private final BoardRepository boardRepository;

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
	public void updateBoard(Board board) {
		boardRepository.save(getBoard(board).update(board));
	}

	@Override
	public void deleteBoard(Board board) {
		boardRepository.deleteById(board.getBoardIdx());
	}

}

```

---

## 기타 사항

```
🎈 어제 적어둔 Spring Security(로그인) 코드 때문에 요청 URI를 바꾸어도 View가 바뀌질 않는다... 우선 로그인 코드를 모두 삭제하고 게시판과 나머지 기능들을 마무리 한 뒤에 로그인 코드를 작성해야겠다.
```
