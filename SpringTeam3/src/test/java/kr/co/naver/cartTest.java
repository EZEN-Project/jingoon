package kr.co.naver;


import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.domain.CartVO;
import kr.co.repository.CartDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class cartTest {
	
	@Inject
	private CartDAO cartDAO;
	
	@Test
	public void testCartCount() {
		int count = cartDAO.getCartCount(1002);
		System.out.println(count);
	}
	
	@Test
	public void testCartPlus() {
		
	}
	
	@Test
	public void testCartSearch() {
		int a = cartDAO.cartSearch(new CartVO(0, 1001, 1015, 2, 6000, 3000));
		System.out.println(a);
	}
	
	@Test
	public void testCartDelete() {
		
	}
}
