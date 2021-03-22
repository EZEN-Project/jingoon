package kr.co.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.domain.QnABoardVO;
import kr.co.repository.QnABoardDAO;
import kr.co.repository.ReplyDAO;

@Service
@Transactional
public class QnaBoardServiceImpl implements QnABoardService {
	@Inject
	private QnABoardDAO dao;
	@Inject
	private ReplyDAO rdao;
	@Override
	public List<QnABoardVO> list(int curPage) {
		// TODO Auto-generated method stub
		return dao.list(curPage);
	}

	@Override
	public int getAmount() {
		// TODO Auto-generated method stub
		return dao.getAmount();
	}

	@Override
	@Transactional
	public void insert(QnABoardVO vo) {
		// TODO Auto-generated method stub
	
			dao.insert(vo);

		
	}

	@Override
	@Transactional
	public void delete(int bnum) {
		// TODO Auto-generated method stub
		rdao.deleteReplies(bnum);
		dao.delete(bnum);
	}

	@Override
	public QnABoardVO read(int bnum) {
		// TODO Auto-generated method stub
		dao.updateViewCnt(bnum);
		return dao.read(bnum);
	}

	@Override
	public QnABoardVO updateUI(int bnum) {
		// TODO Auto-generated method stub
		return dao.updateUI(bnum);
	}

	@Override
	@Transactional
	public void update(QnABoardVO vo) {
		// TODO Auto-generated method stub
		dao.update(vo);
	}



}
