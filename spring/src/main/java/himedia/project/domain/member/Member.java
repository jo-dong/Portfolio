package himedia.project.domain.member;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data @Entity
@Table(name = "member_info")
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
select * from userinfo;

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
	@Positive
	@Column(name = "member_age")
	private Integer memberAge;
	
	// gender
	@Column(name = "gender")
	private String gender;
	
	// member MBTI
	@Column(name = "mbti")
	private String mbti;
	
	// member region
	@Column(name = "region")
	private String region;
	
	// 권한
	private String role;
	
	// 회원가입한 날짜
	@CreationTimestamp
    private Timestamp date;
}
