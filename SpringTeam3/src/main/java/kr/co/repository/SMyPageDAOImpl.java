package kr.co.repository;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.domain.SearchPageTO;
import kr.co.domain.SellVO;
@Repository
public class SMyPageDAOImpl implements SMyPageDAO{
	@Inject
	private SqlSession sqlsession;
	
	private final String NS = "kr.co.smypage";
	
	@Override
	public Integer getAmount(SearchPageTO<SellVO> spt) {
		// TODO Auto-generated method stub
		return sqlsession.selectOne(NS+".getAmount", spt);
	}

	@Override
	public List<SellVO> adminlist(SearchPageTO<SellVO> spt) {
		// TODO Auto-generated method stub
		RowBounds rb = new RowBounds(spt.getStartNum()-1, spt.getPerPage());
		return sqlsession.selectList(NS+".adminlist", spt, rb);
	}

}
