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
	
	private  final String NS = "kr.co.qreply";
	@Override
	public void insert(ReplyVO vo) {
		// TODO Auto-generated method stub
		sqlSession.insert(NS+".insert", vo);
	}

	@Override
	public int update(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.update(NS+".update", map);
	}

	@Override
	public int delete(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.delete(NS+".delete", map);
	}

	@Override
	public int getReplyAmount(int bnum) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+".getReplyAmount", bnum);
	}

	@Override
	public List<ReplyVO> list(int bnum, PageTO<ReplyVO> to) {
		// TODO Auto-generated method stub
		RowBounds rb = new RowBounds(to.getStartNum()-1, to.getPerPage());

		return sqlSession.selectList(NS+".list", bnum,rb);
	}

	@Override
	public void deleteReplies(int bnum) {
		// TODO Auto-generated method stub
		sqlSession.delete(NS+".deleteReplies",bnum);
	}

}
