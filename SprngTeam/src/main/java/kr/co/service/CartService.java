package kr.co.service;

import java.util.List;

import kr.co.domain.CartVO;

public interface CartService {

	int getCartCount(int memberNo);	// 장바구니의 상품 종류
	
	List<CartVO> getCartList(int memberNo);	// 회원의 장바구니 리스트

	int insert(CartVO cartVO);	// 장바구니 상품 추가

	int cartSearch(CartVO cartVO);	// 장바구니 상품 조회

	int cartAmountPlus(CartVO cartVO);	// 장바구니 수량 증가(변경)

	int getAmount(int cartNo);	// 장바구니 상품의 수량 조회

	int delete(int cartNo);	// 장바구니 상품 삭제

	void cartAmountPlusOne(int cartNo);	// 장바구니 상품 수량 증가

	void cartAmountMinusOne(int cartNo);	// 장바구니 상품 수량 감소
}
