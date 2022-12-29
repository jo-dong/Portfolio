create database marry
character set utf8mb4;

use marry;

create table member_info(
   idx bigint auto_increment,
   member_id varchar(255) Not null,
   member_pw varchar (255) Not null,
   member_name varchar(255) Not null,
   member_age int Not null,
   gender varchar(10) Not null,
   mbti varchar(4),
   region varchar(255),
   primary key(idx)
);

drop table member_info;

select * from member_info;

insert into member_info(member_id, member_pw, member_name, member_age, gender, mbti, region)
values('a1234', '1111', '홍길동', 27, '남성', 'ENFP', '인천');

insert into member_info(member_id, member_pw, member_name, member_age, gender, mbti, region)
values('b1234', '2222', '박아무개', 32, '여성', 'ISTJ', '제주');

select * from marry.member_info;

desc member_info;

commit;

select *
from member_info
where member_id like 'b1234';

rollback;

select * from member_info m where m.member_id='a3333';

delete from marry.member_info
where idx in(1,2);

alter table member_info 
       add column date datetime;
       
