package kr.co.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.domain.CartVO;
import kr.co.domain.SellBoardVO;
import kr.co.domain.SellVO;
import kr.co.repository.CartDAO;
import kr.co.repository.SellBoardDAO;
import kr.co.repository.SellDAO;

@Service
public class CartServiceImpl implements CartService {

	@Inject
	private CartDAO cartDAO;
	
	@Inject SellDAO sellDAO;
	
	@Inject SellBoardDAO dao;

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
			//map.put(sellboardNo, sellboarVO);	// img, title, bcount 
						
			// test 임시정보 바인딩
			String img = "/resources/upload/esc.png";
			String title = "소고기(꽃등심)"; 
			
			Map<String, Object> sellboardVO = new HashMap<>();
			sellboardVO.put("img", img);
			sellboardVO.put("title", title);
			sellboardVO.put("bcount", 11);
			map.put(sellboardNo, sellboardVO);
			
			
		}
		return map;
	}

	@Override
	public String insert(CartVO cartVO) {

		// 장바구니 상품 조회
		// 제한개수가 입력값보다 크거나 같을때 bcount와 amount를 비교
		//int sellboardNo = cartVO.getSellboardNo();
		//SellboardVO sellboardVO= SellboardDAO.getSellboard(sellboardNo);
		//int bcount = sellboardVO.getBcount();
		
		int bcount = 11; // 임시로 제한개수 11개 설정

		

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
		int bcount = 11; // 임시로 제한개수 1000개 설정

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

	@Transactional
	@Override
	public int cartPay(Map<String, Object> map) {
		/*
		 1.회원정보 가져오기 - 비밀번호 일치 확인, 불일치 시 리턴
		 2.회원정보 에서 포인트 확인
		 3.장바구니에서 정보 가져오기 - 총 가격 확인
		 4.포인트와 결제가격 비교, 포인트가 모자라면 리턴 
		 5.상품 판매수량이 구매개수보다 부족하면 리턴
		 6.상품 판매수량 업데이트
		 7.판매정보 입력(저장)
		 8.장바구니 삭제
		 9.회원 포인트 업데이트
		 10. 6,7,8,9 트렌젝션 적용
		- 결제 완료 후 마이페이지의 결제내역 창으로 이동
		*/
		
		int success = 1;
		//String id = map.get("id");
		//MemberVO memberVO= memberDAO.getMember(id);
		//int point = memberVO.getPoint();
		//int memberNo = memberVO.getMnum();
		int point = 10000000; // test 포인트
		int memberNo = 1001; // text 회원번호
		
		List<CartVO> list= cartDAO.getCartList(memberNo);
		int payPrice=0;
		for (CartVO cartVO : list) {
			payPrice += cartVO.getaPrice();
		}
		point = point-payPrice;
		if(point < 0) {
			success= -1;
			return success;		// 포인트가 모자라면 중단
		}
		
		// 판매정보(sellVO) 입력
		Integer groupNum = sellDAO.getMaxGroupNum();
		if(groupNum == null) {
			groupNum =1;
		}
		for (CartVO cartVO : list) {
			// 이미지 불러오기
			int sellboardNo = cartVO.getSellboardNo();
			//SellboardVO sellboardVO = sellboardDAO.getSellboard(sellboardNo);
			//String img =sellboardVO.getImg();
			String img = "/resources/upload/esc.png"; 	// 임시 이미지 입력
			
			// 상품 개수 조회, 수정
			//int bcount = sellboardVO.getBcount();
			int bcount	= 10; // 임시저장값
			int amount = cartVO.getAmount();
			bcount -= amount; 
			if(bcount<0) {
				success = -2;
				return success;	// 판매수량이 부족하면 리턴
			}
			//sellboardVO.setBcount(bcount);
			//sellboardDAO.update(sellboardVO); // 판매상품(상품수량) 업데이트 6
			
			// 판매정보 저장 7
			SellVO sellVO = new SellVO(cartVO.getMemberNo(), sellboardNo, amount, cartVO.getaPrice(), img);
			sellVO.setGroupNum(groupNum+1);
			sellDAO.insert(sellVO);
			
			// 장바구니 삭제 8
			cartDAO.delete(cartVO.getCartNo());
		}
		//memberVO.setPoint(point);
		//memberDAO.update(memberVO);	//회원정보(포인트) 업데이트 9
		
		
		// 성공1 음수 실패
		return success;
	}



}
