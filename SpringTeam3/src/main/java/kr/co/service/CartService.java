package kr.co.service;

import java.util.Map;

import kr.co.domain.CartVO;

public interface CartService {

	int getCartCount(int memberNo);	// 장바구니의 상품 종류
	
	Map<Object, Object> getCartList(int memberNo);	// 회원의 장바구니 리스트

	String insert(CartVO cartVO);	// 장바구니 상품 추가

	int cartSearch(CartVO cartVO);	// 장바구니 상품 조회

	int cartAmountPlus(Map<String, Object> map);	// 장바구니 증가(변경)

	int getAmount(int cartNo);	// 장바구니 상품의 수량 조회

	int delete(int cartNo);	// 장바구니 상품 삭제

	int cartAmountMinus(Map<String, Object> map);	// 장바구니 감소

	int getaPrice(int cartNo);	// 상품 결제가격 조회

	int getTotalPrice(int memberNo);	// 장바구니 전체 상품 가격 계산

	int cartPay(Map<String, Object> map);	// 장바구니 결제 진행
}
