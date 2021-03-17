package kr.co.controller;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import kr.co.domain.MemberVO;
import kr.co.domain.PageTO;
import kr.co.domain.SellVO;
import kr.co.domain.TotalVO;
import kr.co.service.MyPageService;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

		@Inject
		private MyPageService service;
		
		
		
		//매출내역 페이지로 이동
		@RequestMapping(value = "/sales", method = RequestMethod.GET)
		public String sales(TotalVO vo) {
			
		
			return "mypage/sales";
		}

		
		//회원정보UI로 이동
		@RequestMapping(value = "/update/{mnum}", method = RequestMethod.GET)
		public String update(Model model,@PathVariable("mnum")int mnum) {
			MemberVO vo = service.updateUI(mnum);
			model.addAttribute("vo", vo);
			
	
			return "mypage/update";
		}
		//회원정보 수정
		@RequestMapping(value = "/update", method = RequestMethod.POST)
		public String update(MemberVO vo) {
			service.update(vo);
			

			return "redirect:/mypage/mypage";
		}
		//마이페이지로 이동
		@RequestMapping(value = "/mypage", method = RequestMethod.GET)
		public String mypage() {
			return "mypage/mypage";
		}
		//결제내역 페이지로 이동
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public String list() {
			return "mypage/list";
		}
		//관리자페이지 및 페이징처리
		@RequestMapping(value = "/adminlist", method = RequestMethod.GET)
		public String adminlist(Model model) {
			
			int curPage = 1;
			PageTO<SellVO> to = new PageTO<SellVO>(curPage);
			
			List<SellVO> list = service.adminlist(curPage);
			model.addAttribute("list", list);
			
			
			int amount = service.getAmount();
			
			to.setAmount(amount);
			to.setList(list);
			
			model.addAttribute("to", to);
			return "mypage/adminlist";
		}
		//관리자페이지 및 페이징처리
		@RequestMapping(value = "/adminlist/{curPage}", method = RequestMethod.GET)
		public String adminlist(Model model, @PathVariable("curPage") String sCurPage) {
			
			int curPage = 1;
			if(sCurPage != null) {
				curPage = Integer.parseInt(sCurPage);
			}
			
			PageTO<SellVO> to = new PageTO<SellVO>(curPage);
			
			int amount = service.getAmount();
			to.setAmount(amount);
			
			
			List<SellVO> list = service.adminlist(curPage);
			model.addAttribute("list", list);
			
			
			to.setList(list);
			model.addAttribute("to", to);
			
			
			
			return "mypage/adminlist";
			
		}
		
		
		
		

	}

