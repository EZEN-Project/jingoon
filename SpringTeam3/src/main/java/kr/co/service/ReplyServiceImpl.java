package kr.co.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.domain.PageTO;
import kr.co.domain.ReplyVO;
import kr.co.repository.QnABoardDAO;
import kr.co.repository.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService{
	@Inject
	private ReplyDAO dao;
	@Inject
	private QnABoardDAO qdao;
	@Transactional
	@Override
	public void insert(ReplyVO vo) {
		// TODO Auto-generated method stub
		dao.insert(vo);
		qdao.updateReplyCnt(vo.getBnum());
	}

	@Override
	@Transactional
	public int update(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.update(map);
	}
	@Transactional
	@Override
	public int delete(Map<String, Object> map) {
		// TODO Auto-generated method stub
		qdao.updateReplyCntMinus(map);
		return dao.delete(map);
	}

	@Override
	public int getReplyAmount(int bnum) {
		// TODO Auto-generated method stub
		return dao.getReplyAmount(bnum);
	}

	@Override
	public List<ReplyVO> list(int bnum, PageTO<ReplyVO> to) {
		// TODO Auto-generated method stub
		return dao.list(bnum,to);
	}

	

}
