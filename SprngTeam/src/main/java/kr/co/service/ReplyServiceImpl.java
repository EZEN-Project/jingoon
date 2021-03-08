package kr.co.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.domain.PageTO;
import kr.co.domain.ReplyVO;
import kr.co.repository.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Inject
	private ReplyDAO replyDAO;
	
//	@Inject
//	private BoardDAO boardDAO;
	
	@Transactional
	@Override
	public void insert(ReplyVO vo) {
		replyDAO.insert(vo);
//		boardDAO.updateReplyCnt(vo.getBno());
	}

	@Override
	public List<ReplyVO> list(int bno, PageTO<ReplyVO> to) {
		// TODO Auto-generated method stub
		return replyDAO.list(bno, to);
	}

	@Override
	public int getReplyAmount(int bno) {
		// TODO Auto-generated method stub
		return replyDAO.getReplyAmount(bno);
	}

	@Transactional
	@Override
	public int delete(Map<String, Object> map) {
		
//		boardDAO.updateReplyCntMinus(Integer.parseInt(map.get("bno").toString()));//그냥 map해도 됨
		return replyDAO.delete(map);
	}

	@Override
	public int update(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return replyDAO.update(map);
	}

}
