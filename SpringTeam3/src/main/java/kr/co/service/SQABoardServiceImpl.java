package kr.co.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.domain.QnABoardVO;
import kr.co.domain.SearchPageTO;
import kr.co.repository.SQABoardDAO;

@Service
public class SQABoardServiceImpl implements SQABoardService{
	@Inject
	private SQABoardDAO dao;
	@Override
	public Integer getAmount(SearchPageTO<QnABoardVO> spt) {
		// TODO Auto-generated method stub
		return dao.getAmount(spt);
	}


	@Override
	public List<QnABoardVO> list(SearchPageTO<QnABoardVO> spt) {
		// TODO Auto-generated method stub
		return dao.list(spt);
	}

}
