package himedia.project.domain.member;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import javax.persistence.CollectionTable;
=======
>>>>>>> f235dcb242764c01c6b88382bdf22d8104db2f61
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.lang.Nullable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Entity
@Table(name = "member_info")
@NoArgsConstructor
public class Member {
/*
-- [테이블 생성] =============================
create table member_info(
   idx bigint auto_increment,
   member_id varchar(255),
   member_pw varchar (255),
   member_name varchar(255),
   member_age int,
   gender varchar(10),
   mbti varchar(4),
   region varchar(255),
   primary key(idx)
);

-- [조회] =====================================
select * from member_info;

-- [추가] =====================================
insert into member_info(member_id, member_pw, member_name, member_age, gender, mbti, region)
values('a1234', '1111', '홍길동', 27, '남성', 'ENFP', '인천');

insert into member_info(member_id, member_pw, member_name, member_age, gender, mbti, region)
values('b1234', '2222', '박아무개', 32, '여성', 'ISTJ', '제주');
*/
	
	// idx : Primary Key
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx")
	private Long idx;
	
	// member ID
	@NotBlank(message = "ID는 필수 입력 항목입니다.")
	@Column(name = "member_id")
	private String memberId;
	
	// member password
	@NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
	@Column(name = "member_pw")
	private String memberPw;
	
	// member name
	@NotBlank(message = "이름은 필수 입력 항목입니다.")
	@Column(name = "member_name")
	private String memberName;
	
	// member age
	@NotNull(message = "나이는 필수 입력 항목입니다.")
	@Positive
	@Column(name = "member_age")
	private Integer memberAge;
	
	// gender
<<<<<<< HEAD
	@NotNull(message = "성별을 선택해주세요.")
=======
>>>>>>> f235dcb242764c01c6b88382bdf22d8104db2f61
	@Enumerated(value = EnumType.STRING)
	@Column(name = "gender")
	private Gender gender;
	
	// member MBTI
	@Enumerated(value = EnumType.STRING)
	@Column(name = "mbti")
	private Mbti mbti;
	
	// member region
	@ElementCollection(targetClass=String.class)
	@Nullable
<<<<<<< HEAD
//	@CollectionTable(name = "region", joinColumns = @JoinColumn(name="id"))
=======
>>>>>>> f235dcb242764c01c6b88382bdf22d8104db2f61
	@Column(name = "region")
	private List<String> region = new ArrayList<>();

}
