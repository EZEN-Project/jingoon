package kr.co.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.domain.PageTO;
import kr.co.domain.ReplyVO;
import kr.co.service.ReplyService;

@RestController
@RequestMapping("/qareply")
public class ReplyController {
	
	@Inject
	private ReplyService replyService;
	//댓글수정
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public String update(@RequestBody Map<String, Object> map) {
		
		int i = replyService.update(map);
		
		if(i == 1) {
			return "o";
		}
		
		return "x";
	}
	//댓글삭제
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public String delete(@RequestBody Map<String, Object> map) {
		
		int i = replyService.delete(map);
		
		if(i==1) {
			return "o";
		}
		
		
		return "x";
	}

	//댓글리스트
	@RequestMapping(value = "/{bnum}/{curPage}", method = RequestMethod.GET)
	public PageTO<ReplyVO> list(@PathVariable("bnum") int bnum, 
					@PathVariable("curPage") int curPage) {
		PageTO<ReplyVO> to = new PageTO<ReplyVO>();
		
		int amount = replyService.getReplyAmount(bnum);
		to.setAmount(amount);
		
		
		to.setCurPage(curPage);
		List<ReplyVO> list = replyService.list(bnum, to);
		to.setList(list);
		
		
		
		return to;
	}
	//댓글 입력
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String insert(@RequestBody Map<String, Object> map) {
		
		int bnum = (int) map.get("bnum");
		String replyText = (String) map.get("replyText");
		String replyer = (String) map.get("replyer");
		
		
		ReplyVO vo = new ReplyVO(bnum, replyText, replyer);
		
		replyService.insert(vo);
		
		if(vo.getRno() == 0) {
			return "x";
		}else {
			return "o";
		}
	}
	
	

}
