package kr.co.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class viewController {

	// 장바구니 목록 페이지연결
	@RequestMapping(value = "/cart/list", method = RequestMethod.GET)
	public String list() {

		return "/cart/list";
	}
	
}
