package kr.co.repository;


import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


import kr.co.domain.MemberVO;
import kr.co.domain.PageTO;
import kr.co.domain.SellVO;
import kr.co.domain.TotalVO;

@Repository
public class MyPageDAOImpl implements MyPageDAO{
	@Inject
	private SqlSession sqlsession;
	
	private final String NS = "kr.co.mypage";
	
	@Override
	public MemberVO updateUI(int mnum) {
		// TODO Auto-generated method stub
		return sqlsession.selectOne(NS+".updateUI", mnum);
	}

	@Override
	public void update(MemberVO vo) {
		// TODO Auto-generated method stub
		sqlsession.update(NS+".update", vo);
	}

	@Override
	public List<SellVO> list(int sellnum) {
		// TODO Auto-generated method stub
		return sqlsession.selectList(NS+".list", sellnum);
	}

	@Override
	public void delete(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sqlsession.update(NS+".delete", map);
	}

	@Override
	public List<SellVO> adminlist(int curPage) {
		// TODO Auto-generated method stub
		PageTO<SellVO> to = new PageTO<SellVO>(curPage);
		
		RowBounds rb = new RowBounds(to.getStartNum()-1, to.getPerPage());
		return sqlsession.selectList(NS+".adminlist", null,rb);
	}

	@Override
	public int getAmount() {
		// TODO Auto-generated method stub
		return sqlsession.selectOne(NS+".getAmount");
	}

	@Override
	public List<TotalVO> chart_day() {
		// TODO Auto-generated method stub
		return sqlsession.selectList(NS+".chart_day");
	}
	public List<TotalVO> chart_month() {
		// TODO Auto-generated method stub
		return sqlsession.selectList(NS+".chart_month");
	}
	public List<TotalVO> chart_year() {
		// TODO Auto-generated method stub
		return sqlsession.selectList(NS+".chart_year");
	}

	


	
	

	

	

}
