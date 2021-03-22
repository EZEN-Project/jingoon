package kr.co.repository;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.domain.PageTO;
import kr.co.domain.QnABoardVO;


@Repository
public class QnABoardDAOImpl implements QnABoardDAO{
	@Inject
	private SqlSession sqlSession;
	
	private final String NS = "kr.co.qnaboard";
	@Override
	public List<QnABoardVO> list(int curPage) {
		// TODO Auto-generated method stub
		PageTO<QnABoardVO> to = new PageTO<QnABoardVO>(curPage);
		
		RowBounds rb = new RowBounds(to.getStartNum()-1, to.getPerPage());
		return sqlSession.selectList(NS+".list", null, rb);
	}

	@Override
	public int getAmount() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+".getAmount");
	}

	@Override
	public void insert(QnABoardVO vo) {
		// TODO Auto-generated method stub
		sqlSession.insert(NS+".insert", vo);
	}

	@Override
	public void delete(int bnum) {
		// TODO Auto-generated method stub
		sqlSession.delete(NS+".delete", bnum);
	}

	@Override
	public QnABoardVO read(int bnum) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+".read", bnum);
	}

	@Override
	public void updateReplyCnt(int bnum) {
		// TODO Auto-generated method stub
		sqlSession.update(NS+".updateReplyCnt", bnum);
	}

	@Override
	public void updateReplyCntMinus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sqlSession.update(NS+".updateReplyCntMinus",map);
	}

	@Override
	public void updateViewCnt(int bnum) {
		// TODO Auto-generated method stub
		sqlSession.update(NS+".updateViewCnt",bnum);
	}

	@Override
	public QnABoardVO updateUI(int bnum) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+".updateUI", bnum);
	}

	@Override
	public void update(QnABoardVO vo) {
		// TODO Auto-generated method stub
		sqlSession.update(NS+".update", vo);
	}



}
