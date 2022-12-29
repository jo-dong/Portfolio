<<<<<<< HEAD
# Day 11 - 12/26(월)
=======
# Day 11 - 12/28(수)
>>>>>>> 7546fcd987d27279d090c6bc09278c17a492d349

## 오늘 한 일

```
<<<<<<< HEAD
🎈 Domain/Controller/Repository가 너무 난잡한 것 같아 다시 정리

🎈 로그인 구현에 관련해 Session/Cookie에 대해 알아봄

🎈 게시판 관련 DB의 charter set을 utf8mb4로 생성하여 후에 게시판 title/content에 이모지(Win + .)를 사용하게 함
=======
🎈 게시판 DB 생성(게시판, 댓글)

🎈 Repository : JPA -> Spring Data JPA로 변경

🎈 Board Entity, Service 작성
>>>>>>> 7546fcd987d27279d090c6bc09278c17a492d349
```

---

## 진행 상황

<<<<<<< HEAD
> Session/Cookie에 대해 정리해놓은 글과 코드를 보고 참고하여 로그인 코드를 작성하였지만 여전히 잘 모르겠다.. 더 공부가 필요😢
>
> > 출처 : https://catsbi.oopy.io/0c27061c-204c-4fbf-acfd-418bdc855fd8

> 너무 난잡하고 Naming도 마음대로인 것 같아 프로젝트를 재생성하여 아래와 같이 다시 정리

<!-- Image -->
![code](https://user-images.githubusercontent.com/111822816/209551870-49f1429e-89d2-4c7a-a1fe-1a8f8aee9af0.png)

---

## Trouble Shooting

```sql
🎈 코드를 정리하며 회원가입 코드를 다시 작성 후 DB 확인

-> Error는 발생하지 않았지만 DB에 데이터가 저장되지 않음

-> MySQL과의 연동에도 문제가 없었고, DB 저장 코드에도 문제를 발견하지 못함

-> 오늘 수업시간에 Auto Commit을 사용하지 않음으로 바꿨던 것이 생각나서 직접 commit;을 작성했더니 DB에 값이 저장됨을 확인
```

<!-- Image -->
![db1](https://user-images.githubusercontent.com/111822816/209551880-e3dc7df6-9600-47a9-b225-f6ffd9a9fdd2.png)
=======
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
>>>>>>> 7546fcd987d27279d090c6bc09278c17a492d349

---

## 기타 사항

```
<<<<<<< HEAD
🎈 앞으로 로그인, 체크박스, 게시판 등 남은 작업이 많다.

순서를 잘 정리해서 최대한 효율적으로 작업을 진행해야한다.
=======
🎈 어제 적어둔 Spring Security(로그인) 코드 때문에 요청 URI를 바꾸어도 View가 바뀌질 않는다... 우선 로그인 코드를 모두 삭제하고 게시판과 나머지 기능들을 마무리 한 뒤에 로그인 코드를 작성해야겠다.
>>>>>>> 7546fcd987d27279d090c6bc09278c17a492d349
```
