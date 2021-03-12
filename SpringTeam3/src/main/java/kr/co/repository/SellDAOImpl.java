package kr.co.repository;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.domain.SellVO;

@Repository
public class SellDAOImpl implements SellDAO{

	@Inject
	private SqlSession sqlSession;
	
	private final String NS = "kr.co.sell";

	@Override
	public Integer getMaxGroupNum() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+".getMaxGroupNum");
	}

	@Override
	public void insert(SellVO sellVO) {
		sqlSession.insert(NS+".insert", sellVO);
		
	}

	
	
}
