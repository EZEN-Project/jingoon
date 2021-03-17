package kr.co.service;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.domain.SearchPageTO;
import kr.co.domain.SellVO;
import kr.co.repository.SMyPageDAO;
@Service
public class SMyPageServiceImpl implements SMyPageService{
	@Inject
	private SMyPageDAO dao;
	
	@Override
	public Integer getAmount(SearchPageTO<SellVO> spt) {
		// TODO Auto-generated method stub
		return dao.getAmount(spt);
	}

	@Override
	public List<SellVO> adminlist(SearchPageTO<SellVO> spt) {
		// TODO Auto-generated method stub						
		return dao.adminlist(spt);
	}

}
