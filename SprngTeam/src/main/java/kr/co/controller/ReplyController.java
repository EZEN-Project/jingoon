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
@RequestMapping("/reply")
public class ReplyController {
	
	@Inject
	private ReplyService replyservice;
	
	// 댓글 수정
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public String update(@RequestBody Map<String, Object> map) {

		int i = replyservice.update(map);	// new replyVO(); 대신 map으로 가능
		if(i == 1) {
			return "o";
		}else {
			return "x";
		}
	}
	
	
	// 댓글 삭제
	@RequestMapping(value ="", method = RequestMethod.DELETE)
	public String delete(@RequestBody Map<String, Object> map) {
		 
		int i = replyservice.delete(map);
		if(i == 1) {
			return "o";
		}
		return "x";
	}
	
	
	// 글보기 , 댓글 리스트
	@RequestMapping(value = "/{bno}/{curPage}", method = RequestMethod.GET)
	public PageTO<ReplyVO> list(@PathVariable("bno") int bno, @PathVariable("curPage") int curPage) {
		PageTO<ReplyVO> to = new PageTO<ReplyVO>();
		
		int amount = replyservice.getReplyAmount(bno);
		
		to.setAmount(amount);
		to.setCurPage(curPage);
		List<ReplyVO> list= replyservice.list(bno, to);
		to.setList(list);
		return to;
	}
	
	//댓글 입력
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String insert(@RequestBody Map<String, Object>map) { //@RequestBody ReplyVO vo 이렇게한다, 지금은 학습용
		int bno = (int) map.get("bno");
		String replyText =(String) map.get("replyText");
		String replyer =(String) map.get("replyer");
		ReplyVO vo = new ReplyVO(bno,replyText,replyer);
		//System.out.println("/bno:"+bno+"/replyText:"+replyText+"/replyer:"+replyer);// test
		replyservice.insert(vo);
		if(vo.getRno() == 0) {
			return "x";
		}else {
			return "o";
		}
	}

}
