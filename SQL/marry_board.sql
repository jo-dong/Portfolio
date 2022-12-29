use marry;

show tables;

drop table marry.comment;
drop table marry.board;

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

create table comment (
	comment_idx bigint NOT NULL auto_increment,
    board_idx bigint NOT NULL,
    content longtext NOT NULL,
    likes int default 0,
    dislikes int default 0,
  # 로그인 해결 후 member_name(FK) 추가
	create_date datetime,
    update_date datetime,
    primary key(comment_idx),
    foreign key(board_idx) references board(board_idx)
    on delete cascade
);

insert into marry.board
values(1, '안녕하세요', '안녕하세요~🎃', 30, 2, now(), sysdate(), 50);

insert into marry.board(title, content, likes, dislikes, create_date, update_date, hit_cnt)
values('여보세요🎪', '내용내용🎈', 30, 2, now(), sysdate(), 40);

select * from marry.member_info;
select * from marry.board;
select * from comment;

delete from board
where board_idx = 6;