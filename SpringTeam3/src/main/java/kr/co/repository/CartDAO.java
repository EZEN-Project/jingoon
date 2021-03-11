package kr.co.repository;

import java.util.List;

import kr.co.domain.CartVO;

public interface CartDAO {

	int getCartCount(int memberNo);	// 장바구니 수량 반환

	List<CartVO> getCartList(int memberNo);	// 회원의 장바구니 리스트

	int insert(CartVO cartVO);	// 장바구니 상품 추가

	int cartSearch(CartVO cartVO);	// 장바구니 상품 조회

	int getAmount(int cartNo);	// 장바구니 상품의 수량 조회

	int delete(int sellboardNo);	// 장바구니 상품의 삭제

	int getaPrice(int cartNo);	// 상품 결제가격 조회

	int cartUpdate(CartVO cartVO);	// 장바구니 상품 변경/추가(수량,가격) 

	int getPrice(int cartNo);	// 장바구니 상품 가격 조회

	
}
