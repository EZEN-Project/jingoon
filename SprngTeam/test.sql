
-- 장바구니 테이블 
create table cart(
cartNo number primary key,
memberNo number not null,
sellboardNo number not null,
amount number default '0'
)
-- goodsNo 컬럼명을 sellboardNo으로 변경(테이블 수정)
ALTER TABLE cart RENAME COLUMN goodsNo TO sellboardNo
-- Test 장바구니 데이터 입력
insert into cart (cartNo, memberNo, sellboardNo, amount) values ('1', '1001', '1001', '2')
insert into cart (cartNo, memberNo, sellboardNo, amount) values ('2', '1001', '1002', '3')
insert into cart (cartNo, memberNo, sellboardNo, amount) 
		values ((SELECT NVL2(MAX(cartNo), MAX(cartNo)+1, 1) FROM cart),'1001','1003','3')
select * from CART where memberNo = '1001' order by cartNo desc