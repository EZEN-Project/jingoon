package kr.co.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.MemberVO;
import kr.co.domain.PageTO;
import kr.co.domain.QnABoardVO;
import kr.co.service.QnABoardService;


@Controller
@RequestMapping("/qaboard")
public class QABoardController {
	@Inject
	private QnABoardService boardService;
	
	//Q&A게시물 수정
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(QnABoardVO vo) {
		
		boardService.update(vo);
		
		
		
		return "redirect:/qaboard/read/"+vo.getBnum();
	}
	
	
	//Q&A게시물UI
	@RequestMapping(value = "/update/{bnum}", method = RequestMethod.GET)
	public String update(@PathVariable("bnum") int bnum, Model model) {
		QnABoardVO vo = boardService.updateUI(bnum);
		model.addAttribute("vo", vo);
		
		return "qaboard/update";
	}
	//Q&A게시물 자세히보기
	@RequestMapping(value = "/read/{bnum}", method = RequestMethod.GET)
	public String read(@PathVariable("bnum") int bnum, Model model,HttpSession session) {
	   MemberVO memberVO =(MemberVO) session.getAttribute("login");
		model.addAttribute("memberVO", memberVO);
	   QnABoardVO vo = boardService.read(bnum);
	   model.addAttribute("vo", vo);
		
		return "qaboard/read";
	}
	//Q&A게시물 삭제
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	public String delete(int bnum) {
		
		boardService.delete(bnum);
		
		return "redirect:/qaboard/list";
	}
	//Q&A 글쓰기
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(QnABoardVO vo) {
		
		boardService.insert(vo);
			
		return "redirect:/qaboard/read/"+vo.getBnum();
	}
	
	//Q&A글쓰기 페이지로 이동
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insert() {
		
	}
	
	//Q&A페이징 처리
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		int curPage = 1;
		PageTO<QnABoardVO> to = new PageTO<QnABoardVO>(curPage);
		
		List<QnABoardVO> list = boardService.list(curPage);
		model.addAttribute("list", list);
		
		
		int amount = boardService.getAmount();
		
		to.setAmount(amount);
		to.setList(list);
		
		model.addAttribute("to", to);
		
		return "qaboard/list";
	}
	
	
	//Q&A페이징 처리
	@RequestMapping(value = "/list/{curPage}", method = RequestMethod.GET)
	public String list(Model model, @PathVariable("curPage") String sCurPage) {
		
		int curPage = 1;
		if(sCurPage != null) {
			curPage = Integer.parseInt(sCurPage);
		}
		
		PageTO<QnABoardVO> to = new PageTO<QnABoardVO>(curPage);
		
		int amount = boardService.getAmount();
		to.setAmount(amount);
		
		if (curPage > to.getTotalPage()) {
			return "redirect:/qaboard/list/"+1;
		}else {
			List<QnABoardVO> list = boardService.list(curPage);
			model.addAttribute("list", list);
			
			
			to.setList(list);
			model.addAttribute("to", to);
			
			
			
			return "qaboard/list";
		}
		
		
	}
}
