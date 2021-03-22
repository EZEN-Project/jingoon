package kr.co.repository;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.domain.QnABoardVO;
import kr.co.domain.SearchPageTO;

@Repository
public class SQABoardDAOImpl implements SQABoardDAO{
	@Inject
	private SqlSession sqlSession;
	
	private final String NS = "kr.co.sqaboard";
	@Override
	public Integer getAmount(SearchPageTO<QnABoardVO> spt) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+".getAmount", spt);
	}

	@Override
	public List<QnABoardVO> list(SearchPageTO<QnABoardVO> spt) {
		// TODO Auto-generated method stub
		RowBounds rb = new RowBounds(spt.getStartNum()-1, spt.getPerPage());

		return sqlSession.selectList(NS+".list", spt, rb);
	}

}
