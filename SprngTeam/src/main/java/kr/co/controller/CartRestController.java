package kr.co.controller;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
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
	
	// 상품 개수 조회
	@RequestMapping(value = "/getAmount/{cartNo}",method = RequestMethod.GET)
	public int getAmount(@PathVariable("cartNo") int cartNo) {
		return cartService.getAmount(cartNo);
	}
	
	
	// 장바구니에 상품 추가
	@RequestMapping(value = "", method = RequestMethod.POST,
			produces = "application/text; charset=utf-8")
	public String insert(@RequestBody Map<String, Object> map) {
	/*	로그인 권한 적용후 주석 해제	
		Object login= session.getAttribute("login");
		String id = login.getId;
		int memberNo = memberService.getMnum((String)map.get("id"));
	*/
		int memberNo = 1001;	// test값
		map.put("memberNo", memberNo);
		String getSellboardNo= map.get("sellboardNo").toString();
		int sellboardNo = Integer.valueOf(getSellboardNo);
		String getAmount = map.get("amount").toString();
		int amount = Integer.valueOf(getAmount);
		String getPrice = map.get("price").toString();
		int price = Integer.valueOf(getPrice);
		String getaPrice = map.get("aPrice").toString();
		int aPrice = Integer.valueOf(getaPrice);
			
		CartVO cartVO = new CartVO(memberNo, sellboardNo, amount, aPrice, price);
			
		return cartService.insert(cartVO);
		
	}
	
	// 장바구니 상품 개수 플러스
	@RequestMapping(value = "/cartAmountPlus", method = RequestMethod.PUT)
	public int cartAmountPlusOne(@RequestBody Map<String, Object> map) {
		
		return cartService.cartAmountPlus(map);
	}
	
	// 장바구니 상품 개수 마이너스
	@RequestMapping(value = "/cartAmountMinus", method = RequestMethod.PUT)
	public int cartAmountMinusOne(@RequestBody Map<String, Object> map) {

		return cartService.cartAmountMinus(map);
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
		
		return cartService.getTotalPrice(memberNo);
	}
	
	
	// 장바구니 리스트( + 상품정보)
	@RequestMapping(value = "/getCartList", method = RequestMethod.GET)
	public Map<Object, Object> getCart() {
				
		//int memberNo = memberService.getMnum(id);
		int memberNo = 1001;
		
		return cartService.getCartList(memberNo);
		
	}
}
