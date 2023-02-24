-- tblBoard --
create table tblBoard(
	idx int not null, -- 자동증가가 아닌 댓글이 달리면 댓글의 게시판에 있는 idx의 최대값+1 -- 
	memID varchar(20) not null, -- 댓글의 아이디를 저장하기 위한 변수 -- 
	title varchar(100) not null, 
	content varchar(2000) not null,
	writer varchar(30) not null,
	indate datetime default now(),
	count int default 0,
	-- 댓글관련 속성 --
	boardGroup int, -- 원글(부모글과 댓글을 하나의 그룹으로 묶음) --
	boardSequence int,-- 같은 그룹안에서 답글이 달리는 순서-- 
	boardLevel int,-- 댓글 단계별 들여쓰기 -- 
	boardAvailable int,-- 원글 삭제 여부 : 원글이 있는 경우 댓글 삭제불가 --
	primary key(idx)
);

select max(idx) from tblBoard; -- NULL -> 1 , 2+1 -> 3
select IFNULL(max(idx)+1,1) from tblBoard;
select IFNULL(max(boardGroup)+1,0) from tblBoard;

insert into tblBoard
select IFNULL(max(idx)+1,1),'bit01','게시판 연습','게시판 연습','관리자',
now(),0,IFNULL(max(boardGroup)+1,0),0,0,1
from tblBoard;


select * from tblBoard;

create table tblMember(
  memID varchar(50) not null, -- 회원ID
  memPwd varchar(50) not null, -- 회원비번
  memName varchar(50) not null, -- 회원이름
  memPhone varchar(50) not null, -- 회원전화번호
  memAddr varchar(100), -- 회원주소
  latitude decimal(13,10), -- 현재위치위도
  longitude decimal(13,10), -- 현재위치경도
  primary key(memID)
);

insert into tblMember(memID,memPwd,memName,memPhone) 
values ('bit01','bit01','관리자1','010-1111-1111');
insert into tblMember(memID,memPwd,memName,memPhone) 
values ('bit02','bit02','관리자2','010-2222-2222');
insert into tblMember(memID,memPwd,memName,memPhone) 
values ('bit03','bit03','관리자3','010-3333-3333');

select * from tblMember;

delete from tblBoard where idx=1;
delete from tblBoard where idx=2;
delete from tblBoard where idx=3;


