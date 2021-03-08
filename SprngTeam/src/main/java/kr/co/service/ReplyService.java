package kr.co.service;

import java.util.List;
import java.util.Map;

import kr.co.domain.PageTO;
import kr.co.domain.ReplyVO;

public interface ReplyService {
	
	public void insert(ReplyVO vo);
	
	List<ReplyVO> list(int bno, PageTO<ReplyVO> to);

	public int getReplyAmount(int bno);

	public int delete(Map<String, Object> map);

	public int update(Map<String, Object> map);

}
