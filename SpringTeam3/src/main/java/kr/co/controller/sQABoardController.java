package kr.co.controller;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.QnABoardVO;
import kr.co.domain.SearchPageTO;



import kr.co.service.SQABoardService;

@Controller
@RequestMapping("/sqaboard")
public class sQABoardController {

		@Inject
		private SQABoardService sService;
	
		//Q&A 게시판 검색기능
		@RequestMapping(value = "/list/{searchType}/{keyword}/{curPage}", method=RequestMethod.GET)
		public String list(@PathVariable("searchType") String searchType,
				@PathVariable("keyword") String keyword,
				@PathVariable("curPage") int curPage,
				Model model) {
			
			SearchPageTO<QnABoardVO> spt = new SearchPageTO<QnABoardVO>(searchType, keyword, curPage);
			Integer amount = sService.getAmount(spt);
			if(amount == null) {
				amount = 0;
			}
			spt.setAmount(amount);
			
			List<QnABoardVO> list = sService.list(spt);
			spt.setList(list);
			
			model.addAttribute("spt", spt);
			
			
			return "sqaboard/list";
		}
		
		
		
		

	}

