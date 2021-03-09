
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
sellDate date default sysdate
)
