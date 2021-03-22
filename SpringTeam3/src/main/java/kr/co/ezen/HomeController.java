package kr.co.ezen;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.SellBoardVO;
import kr.co.service.SellBoardService;

@Controller
public class HomeController {
	
	@Inject
	private SellBoardService sellboardService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Model model) {
		
		List<SellBoardVO> vo= sellboardService.list();
		
		for (SellBoardVO sellBoardVO : vo) {
			String img = sellBoardVO.getContent();
			
			String a= img.substring(0, 12);
			String b=img.substring(14);
			img = a+b;
			sellBoardVO.setContent(img);
		}
		
		model.addAttribute("vo", vo);
		return "main";
	}
	
}
