package kr.co.repository;



import java.util.List;
import java.util.Map;

import kr.co.domain.MemberVO;
import kr.co.domain.SellVO;
import kr.co.domain.TotalVO;

public interface MyPageDAO {
	MemberVO updateUI(int mnum);
	public void update(MemberVO vo);
	List<SellVO> list(int sellnum);
	void delete(Map<String, Object> map);
	List<SellVO> adminlist(int curPage);
	int getAmount();
	
	List<TotalVO> chart_day();
	List<TotalVO> chart_month();
	List<TotalVO> chart_year();
	

	
	
}
