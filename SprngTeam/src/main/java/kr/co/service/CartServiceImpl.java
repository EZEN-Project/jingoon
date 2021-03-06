package kr.co.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.domain.CartVO;
import kr.co.repository.CartDAO;

@Service
public class CartServiceImpl implements CartService {

	@Inject
	private CartDAO cartDAO;

	@Override
	public int getCartCount(int memberNo) {
		// TODO Auto-generated method stub
		return cartDAO.getCartCount(memberNo);
	}

	@Override
	public Map<Object, Object> getCartList(int memberNo) {
		Map<Object, Object> map = new HashMap<>();
		
		// 장비구니목록 정보 가져오기
		List<CartVO> cartList = cartDAO.getCartList(memberNo);
		
		map.put("cartList", cartList);
		
		// 상품 정보(+이미지) 가져오기
		for (CartVO cartVO : cartList) {
			int sellboardNo = cartVO.getSellboardNo();
			//SellBoardVO sellboarVO= sellboardDAO.getSellboard(sellboardNo);	// 판매게시물 번호로 판매게시물 정보 가져오기
			//map.put(sellboardNo, sellboarVO); 
			
			// test 임시정보 바인딩
			String img = "/resources/upload/esc.png";
			String title = "소고기(꽃등심)"; 
			
			Map<String, Object> sellboardVO = new HashMap<>();
			sellboardVO.put("img", img);
			sellboardVO.put("title", title);
			
			map.put(sellboardNo, sellboardVO);
			
			
		}
		return map;
	}

	@Override
	public String insert(CartVO cartVO) {

		// 장바구니 상품 조회
		// 제한개수가 입력값보다 크거나 같을때 bcount와 amount를 비교
		// int bcount = sellboardDAO.getbcount(sellboardNo);
		int bcount = 1000; // 임시로 제한개수 1000개 설정

		

		int success = 0;
		int cartNo = cartDAO.cartSearch(cartVO);
		if (cartNo <= 0) { // 기존 상품 아니면 입력
			if (cartVO.getAmount() <= bcount) {
				success = cartDAO.insert(cartVO);
			} else {
				return "판매상품 수량이 부족합니다";
			}

		} else { // 기존 상품 이면 amounnt, aPrice 증가
			int amount = cartVO.getAmount();// 추가할 amount
			amount = amount + cartDAO.getAmount(cartNo);
			if (amount <= bcount) {
				cartVO.setCartNo(cartNo);
				cartVO.setAmount(amount);
				int aPrice = amount * cartVO.getPrice();
				cartVO.setaPrice(aPrice);
				success = cartDAO.cartUpdate(cartVO);
			} else {
				return "판매상품 수량이 부족합니다";
			}
		}

		return success >= 1 ? "장바구니에 상품이 " + cartVO.getAmount() + "개가 되었습니다" : "장바구니에 담기가 실패하였습니다.";

	}

	@Override
	public int cartSearch(CartVO cartVO) {
		// TODO Auto-generated method stub
		return cartDAO.cartSearch(cartVO);
	}
	
	// +버튼으로 상품 추가 할때
	@Override
	public int cartAmountPlus(Map<String, Object> map) {
		String cartNoStr= map.get("cartNo").toString();
		int cartNo = Integer.valueOf(cartNoStr);
		
		// int bcount = sellboardService.getbcount(sellboardNo);
		int bcount = 1000; // 임시로 제한개수 1000개 설정

		int amount = cartDAO.getAmount(cartNo);
		if (amount >= bcount) {
			return amount;
		}
		amount +=1 ;
		int price = cartDAO.getPrice(cartNo);
		int aPrice = price * amount;
		CartVO cartVO = new CartVO();
		cartVO.setCartNo(cartNo);
		cartVO.setAmount(amount);
		cartVO.setaPrice(aPrice);
		
		int success = cartDAO.cartUpdate(cartVO);
		if( success == 1){
			return cartDAO.getAmount(cartNo);
		}else {
			return -1;
		}
		
	}
	
	// - 버튼으로 상품 수량 감소
	@Override
	public int cartAmountMinus(Map<String, Object> map) {
		String cartNoStr= map.get("cartNo").toString();
		int cartNo = Integer.valueOf(cartNoStr);
		
		int amount = cartDAO.getAmount(cartNo);
		if (amount == 1) {
			return amount;
		}
		amount -=1 ;
		int price = cartDAO.getPrice(cartNo);
		int aPrice = price * amount;
		CartVO cartVO = new CartVO();
		cartVO.setCartNo(cartNo);
		cartVO.setAmount(amount);
		cartVO.setaPrice(aPrice);
		
		int success = cartDAO.cartUpdate(cartVO);
		if( success == 1){
			return cartDAO.getAmount(cartNo);
		}else {
			return -1;
		}
		
	}

	@Override
	public int getAmount(int cartNo) {
		// TODO Auto-generated method stub
		return cartDAO.getAmount(cartNo);
	}

	@Override
	public int delete(int cartNo) {
		return cartDAO.delete(cartNo);

	}


	@Override
	public int getaPrice(int cartNo) {
		// TODO Auto-generated method stub
		return cartDAO.getaPrice(cartNo);
	}

	@Override
	public int getTotalPrice(int memberNo) {
		int totalPrice=0;
		List<CartVO> cartList = cartDAO.getCartList(memberNo);
		for (CartVO cartVO : cartList) {
			totalPrice += cartVO.getaPrice();
		}
		return totalPrice;
	}

	// 장바구니 결제 진행
	// 1.회원정보 가져오기 - 비밀번호 일치 확인
	// 2.회원정보 에서 포인트 확인
	// 3.장바구니에서 정보 가져오기 - 총 가격 확인
	// 4.포인트와 결제가격 비교
	// 5.포인트에서 결제가격을 빼서 포인트 업데이트
	// 6.장바구니의 정보로 판매정보 입력
	// 7.장바구니 삭제
	// 8. 6,7,8 트렌젝션 적용
	@Transactional
	@Override
	public int cartPay(Map<String, Object> map) {
				
		
		// 0실패 1성공
		return 0;
	}



}
