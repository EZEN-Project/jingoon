
-- 장바구니 테이블 
create table cart(
cartNo number primary key,
memberNo number not null,
sellboardNo number not null,
amount number default '0',
aPrice number,
price number
)
-- goodsNo 컬럼명을 sellboardNo으로 변경(테이블 수정)
ALTER TABLE cart RENAME COLUMN goodsNo TO sellboardNo
-- 장바구니 테이블에 상품 결제가격(가격*개수) 추가
alter table cart add aPrice number;
-- 장바구니 테이블에 상품 개당가격 추가
alter table cart add price number;
-- Test 장바구니 데이터 입력
insert into cart (cartNo, memberNo, sellboardNo, amount, aPrice, price) values ('1', '1001', '1001', '2', '18000', '9000')

select * from cart 
delete from cart

-- 결제 테이블
create table sell(
sellNum number primary key,
buyNum number,
sellboardNo number,
amount number,
aPrice number,
groupNum number,
sellDate date default sysdate,
img varchar2(200)
)

-- 결제 테이블에 결제상품 이미지 추가
alter table sell add img varchar2(200)

select * from sell

-- 상품 게시글 
create table sellboard(
bnum number(6) primary key,
title varchar2(300) not null,
writer varchar2(300) not null,
content varchar2(300) not null,
cost number(10) DEFAULT 0,
price number(10) DEFAULT 0,
veiwcnt number(6) default 0,
bcount number(6) default 1
)
select * from sellboard
-- 이미지 path 테이블
create table attach(
id number(8) primary key,
fullname varchar2(200) not null,
bnum number(6),
regdate date default sysdate,
constraint fk_attach_bnum foreign key(bnum) references sellboard(bnum)
on delete cascade
)

create table member(
mnum number(6) primary key,
id varchar2(10) not null,
email varchar2(20) not null,
pw varchar2(15) not null,
name varchar2(12) not null,
phone varchar2(15) not null,
address varchar2(300) not null,
regdate DATE DEFAULT SYSDATE,
updatedate DATE DEFAULT SYSDATE,
birth date not null,
point number(10) DEFAULT 0,
mtype number(10) not null,
memo varchar2(100)
)

create table qboard(
bnum number(6) primary key,
title varchar2(300) not null,
writer varchar2(300) not null,
content varchar2(300) not null,
veiwcnt number(6) default 0,
regdate DATE DEFAULT SYSDATE,
updatedate DATE DEFAULT SYSDATE
)


select selldate, sum(aprice) as total
        from ((SELECT to_char(selldate, 'yy/fmmm/dd') selldate,aprice FROM sell))
        GROUP BY selldate order by selldate asc



