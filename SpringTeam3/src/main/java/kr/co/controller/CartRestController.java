package kr.co.controller;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.domain.CartVO;
import kr.co.domain.MemberVO;
import kr.co.service.CartService;
import kr.co.service.MemberService;

@RestController
@RequestMapping("/cart")
public class CartRestController {
	
	
	@Inject
	private CartService cartService;
	
	@Inject MemberService memberService;
	
	// 결제하기 성공 : 현재 포인트 반환 
	// -3 비밀번호 오류
	// -1 포인트 부족
	// -2 판매수량 부족
	@RequestMapping(value = "/pay",method = RequestMethod.POST)
	public int pay(@RequestBody Map<String, Object> map, HttpSession session) {
		String pw =(String) map.get("pw");
		MemberVO memberVO =(MemberVO) session.getAttribute("login");
		if(!pw.equals(memberVO.getPw())) {
			return -3;
		}
		map.put("id", memberVO.getId());
		int success = cartService.cartPay(map);
		
		return success;
	}
	
	
	// 상품 개수 조회
	@RequestMapping(value = "/getAmount/{cartNo}",method = RequestMethod.GET)
	public int getAmount(@PathVariable("cartNo") String cartNoStr) {
		int cartNo = Integer.valueOf(cartNoStr);
		return cartService.getAmount(cartNo);
	}
	
	
	// 장바구니에 상품 추가
	@RequestMapping(value = "", method = RequestMethod.POST,
			produces = "application/text; charset=utf-8")
	public String insert(@RequestBody Map<String, Object> map, HttpSession session) {
	
		MemberVO memberVO= (MemberVO) session.getAttribute("login");
				
		int memberNo = memberVO.getMnum();	
		map.put("memberNo", memberNo);
		String getSellboardNo= map.get("sellboardNo").toString();
		int sellboardNo = Integer.valueOf(getSellboardNo);
		String getAmount = map.get("amount").toString();
		int amount = Integer.valueOf(getAmount);
		String getPrice = map.get("price").toString();
		int price = Integer.valueOf(getPrice);
		int aPrice = amount*price;
			
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
	// 장바구니 상품 삭제
	@RequestMapping(value = "/allDelete", method = RequestMethod.GET)
	public int allDelete(HttpSession session) {
		MemberVO vo =(MemberVO) session.getAttribute("login");
		int memberNo = vo.getMnum();
		int success = cartService.allDelete(memberNo);
		return success;
	}
	
	// 상품의 구매종류
	@RequestMapping(value = "/getCartCount", method = RequestMethod.GET)
	public int getCartCount(HttpSession session) {
		MemberVO vo =(MemberVO) session.getAttribute("login");
		int memberNo = vo.getMnum();

		return cartService.getCartCount(memberNo);
	}
	
	// 장바구니 상품의 총 가격(가격 * 개수)
	@RequestMapping(value = "/getTotalPrice", method = RequestMethod.GET)
	public int getTotalPrice(HttpSession session) {
		
		MemberVO vo =(MemberVO) session.getAttribute("login");
		int memberNo = vo.getMnum();
		
		return cartService.getTotalPrice(memberNo);
	}
	
	
	// 장바구니 리스트( + 상품정보)
	@RequestMapping(value = "/getCartList", method = RequestMethod.GET)
	public Map<Object, Object> getCart(HttpSession session) {
				
		MemberVO vo =(MemberVO) session.getAttribute("login");
		int memberNo = vo.getMnum();
		
		return cartService.getCartList(memberNo);
		
	}
}
