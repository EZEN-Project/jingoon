package kr.co.controller;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import kr.co.domain.SearchPageTO;
import kr.co.domain.SellVO;

import kr.co.service.SMyPageService;

@Controller
@RequestMapping("/smypage")
public class sMyPageController {

		@Inject
		private SMyPageService sService;
	
		
		@RequestMapping(value = "/adminlist/{searchType}/{keyword}/{curPage}", method=RequestMethod.GET)
		public String adminlist(@PathVariable("searchType") String searchType,
				@PathVariable("keyword") String keyword,
				@PathVariable("curPage") int curPage,
				Model model) {
			
			SearchPageTO<SellVO> spt = new SearchPageTO<SellVO>(searchType, keyword, curPage);
			Integer amount = sService.getAmount(spt);
			if(amount == null) {
				amount = 0;
			}
			spt.setAmount(amount);
			
			List<SellVO> list = sService.adminlist(spt);
			spt.setList(list);
			
			model.addAttribute("spt", spt);
			
			
			return "smypage/adminlist";
		}
		
		
		
		

	}

