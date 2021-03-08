package kr.co.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.domain.CartVO;
import kr.co.repository.CartDAO;

@Service
public class CartServiceImpl implements CartService{

	@Inject
	private CartDAO cartDAO;

	@Override
	public int getCartCount(int memberNo) {
		// TODO Auto-generated method stub
		return cartDAO.getCartCount(memberNo);
	}

	@Override
	public List<CartVO> getCartList(int memberNo) {
		// TODO Auto-generated method stub
		return cartDAO.getCartList(memberNo);
	}

	@Override
	public int insert(CartVO cartVO) {

		return cartDAO.insert(cartVO);
	}

	@Override
	public int cartSearch(CartVO cartVO) {
		// TODO Auto-generated method stub
		return cartDAO.cartSearch(cartVO);
	}

	@Override
	public int cartAmountPlus(CartVO cartVO) {
		// TODO Auto-generated method stub
		return cartDAO.cartAmountPlus(cartVO);
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
	public void cartAmountPlusOne(int cartNo) {
		cartDAO.cartAmountPlusOne(cartNo);
		
	}

	@Override
	public void cartAmountMinusOne(int cartNo) {
		cartDAO.cartAmountMinusOne(cartNo);
		
	}
	
	
}
