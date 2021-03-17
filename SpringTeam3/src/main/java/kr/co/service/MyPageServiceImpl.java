package kr.co.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.domain.MemberVO;

import kr.co.domain.SellVO;
import kr.co.domain.TotalVO;
import kr.co.repository.MyPageDAO;
@Service
public class MyPageServiceImpl implements MyPageService{
	@Inject
	private MyPageDAO dao;
	@Override
	public MemberVO updateUI(int mnum) {
		// TODO Auto-generated method stub
		MemberVO vo =dao.updateUI(mnum);
		return vo;
	}
	@Override
	public void update(MemberVO vo) {
		// TODO Auto-generated method stub
		dao.update(vo);
	}
	@Override
	public List<SellVO> list(int sellnum) {
		// TODO Auto-generated method stub
		return dao.list(sellnum);
	}
	@Override
	public void delete(Map<String, Object> map) {
		// TODO Auto-generated method stub
		dao.delete(map);
	}
	@Override
	public List<SellVO> adminlist(int curPage) {
		// TODO Auto-generated method stub
		return dao.adminlist(curPage);
	}
	@Override
	public int getAmount() {
		// TODO Auto-generated method stub
		
		
		return dao.getAmount();
	}
	@Override
	public List<TotalVO> chart_day() {
		// TODO Auto-generated method stub
	
		
		
		return dao.chart_day();
	}
	@Override
	public List<TotalVO> chart_month() {
		// TODO Auto-generated method stub
	
		
		
		return dao.chart_month();
	}

	@Override
	public List<TotalVO> chart_year() {
		// TODO Auto-generated method stub
	
		
		
		return dao.chart_year();
	}



	

	

	

}
