package kr.co.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.domain.SellBoardVO;

@Repository
public class SellBoardDAOImpl implements SellBoardDAO{

	@Inject
	private SqlSession sqlSession;
	
	private final String NS = "kr.co.sellboard";
	
	@Override
	public void insert(SellBoardVO vo) {
		sqlSession.insert(NS+".insert", vo);
		
	}

	@Override
	public SellBoardVO read(int bnum) {
		
		return sqlSession.selectOne(NS+".read", bnum);
	}

	@Override
	public List<String> getAttaches(int bnum) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NS+".getAttaches", bnum);
	}

	@Override
	public SellBoardVO updateUI(int bnum) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+".updateUI", bnum);
	}

	@Override
	public void update(SellBoardVO vo) {
		sqlSession.update(NS+".update", vo);
		
	}

	@Override
	public void delete(int bnum) {
		sqlSession.delete(NS+".delete", bnum);
		
	}
	
	@Override
	public void addAttach(String fullName, int bnum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fullName", fullName);
		map.put("bnum", bnum);
		sqlSession.insert(NS+".addAttach", map);
		
	}

}
