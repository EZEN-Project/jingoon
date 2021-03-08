package kr.co.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.domain.CartVO;
import kr.co.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartRestController {

	@Inject
	private CartService cartService;
	
	// 장바구니에 상품 추가
	@RequestMapping(value = "", method = RequestMethod.POST,
			produces = "application/text; charset=utf-8")
	public String insert(@RequestBody Map<String, Object> map) {
	/*	로그인 권한 적용후 주석 해제	
		Object login= session.getAttribute("login");
		String id = login.getId;
		int memberNo = memberService.getMnum((String)map.get("id"));
	*/
		int memberNo = 1001;	// text값
		String sellboardNo= map.get("sellboardNo").toString();
		String amount = map.get("amount").toString();
		CartVO cartVO = new CartVO(memberNo, Integer.valueOf(sellboardNo) , Integer.valueOf(amount));
		
		// 장바구니 상품 조회
		int AddAmount = 0;
		int cartNo = cartService.cartSearch(cartVO);
		if(cartNo <= 0) {
			// 기존 상품 아니면 입력
			AddAmount = cartService.insert(cartVO);
		}else {
			// 기존 상품 이면 amounnt 증가
			AddAmount = cartService.cartAmountPlus(cartVO);
		}
		
		return AddAmount >=1 ? "장바구니에 상품이 "+cartVO.getAmount()+"개 추가되었습니다." : "장바구니에 담기가 실패하였습니다." ;
		
	}
	
	// 장바구니 상품 개수 플러스
	@RequestMapping(value = "/cartAmountPlusOne", method = RequestMethod.PUT)
	public int cartAmountPlusOne(@RequestBody Map<String, Object> map) {
		String cartNoStr= map.get("cartNo").toString();
		int cartNo = Integer.valueOf(cartNoStr);
		// 상품의 제한 갯수보다 작은지 비교문 추가
		cartService.cartAmountPlusOne(cartNo);
		return cartService.getAmount(cartNo);
	}
	
	// 장바구니 상품 개수 마이너스
	@RequestMapping(value = "/cartAmountMinusOne", method = RequestMethod.PUT)
	public int cartAmountMinusOne(@RequestBody Map<String, Object> map) {
		String cartNoStr= map.get("cartNo").toString();
		int cartNo = Integer.valueOf(cartNoStr);
		int amount = cartService.getAmount(cartNo);
		if(amount <= 1) {
			return amount;
		}
		cartService.cartAmountMinusOne(cartNo);
		return cartService.getAmount(cartNo);
	}
	
	// 장바구니 상품 삭제
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public int delete(@RequestBody Map<String, Object> map) {
		int cartNo = Integer.parseInt(map.get("cartNo").toString());
		int success = cartService.delete(cartNo);
		return success;
	}
	
	// 상품의 구매종류
	@RequestMapping(value = "/getCartCount", method = RequestMethod.GET)
	public int getCartCount() {
		//int memberNo = memberService.getMnum(id);
		int memberNo = 1001;

		return cartService.getCartCount(memberNo);
	}
	
	// 장바구니 상품의 총 가격(가격 * 개수)
	@RequestMapping(value = "/getTotalPrice", method = RequestMethod.GET)
	public int getTotalPrice() {
		
		//int memberNo = memberService.getMnum(id);
		int memberNo = 1001;
		int totalPrice=0;
		List<CartVO> cartList = cartService.getCartList(memberNo);
		for (CartVO cartVO : cartList) {
			//int sellboardNo = cartVO.getSellboardNo();
			//int price = sellboardService.getPrice(sellboardNo);
			int amount = cartService.getAmount(cartVO.getCartNo());
			
			int price = 9000;	// 테스트용 가격
			totalPrice += price * amount; 
		}
		return totalPrice;
	}
	
	
	// 장바구니 리스트( + 상품정보)
	@RequestMapping(value = "/getCartList", method = RequestMethod.GET)
	public Map<Object, Object> getCart() {
		
		//int memberNo = memberService.getMnum(id);
		int memberNo = 1001;
		
		
		Map<Object, Object> map = new HashMap<>();
		// 장비구니목록 정보 가져오기
		List<CartVO> cartList = cartService.getCartList(memberNo);
		
		map.put("cartList", cartList);
		
		// 상품 정보(+이미지) 가져오기
		for (CartVO cartVO : cartList) {
			int sellboardNo = cartVO.getSellboardNo();
			//SellBoardVO sellboarVO= sellboardService.getSellboard(sellboardNo);	// 판매게시물 번호로 판매게시물 정보 가져오기
			//map.put(sellboardNo, sellboarVO); 
			
			// test 임시정보 바인딩
			String img = "/resources/upload/esc.png";
			String content = "상품내용입니다. 몸에 좋고 맛있어요!"; 
			int price = 9000;
			Map<String, Object> sellboardVO = new HashMap<>();
			sellboardVO.put("img", img);
			sellboardVO.put("content", content);
			sellboardVO.put("price", price);
			
			map.put(sellboardNo, sellboardVO);
			
			
		}
		
						
		return map;
		
	}
}
