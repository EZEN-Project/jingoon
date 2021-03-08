package kr.co.repository;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.domain.PageTO;
import kr.co.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO{

	@Inject
	private SqlSession sqlSession;
	
	private final String NS = "kr.co.reply";

	@Override
	public void insert(ReplyVO vo) {
		sqlSession.insert(NS+".insert", vo);
	}

	@Override
	public List<ReplyVO> list(int bno, PageTO<ReplyVO> to) {
		RowBounds rb = new RowBounds(to.getStartNum()-1, to.getPerPage());
		
		return sqlSession.selectList(NS+".list", bno, rb);
	}

	@Override
	public int getReplyAmount(int bno) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+".getReplyAmount", bno);
	}

	@Override
	public int delete(Map<String, Object> map) {
		int i = sqlSession.delete(NS+".delete", map);
		return i;
	}

	@Override
	public int update(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.update(NS+".update", map);
	}

	@Override
	public void deleteReplies(int bno) {
		sqlSession.delete(NS+".deleteReplies", bno);
		
	}
	
}
