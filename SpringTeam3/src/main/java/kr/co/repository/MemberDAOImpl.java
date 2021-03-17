package kr.co.repository;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.domain.LoginDTO;
import kr.co.domain.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO{
	
	@Inject
	private SqlSession sqlSession;
	
	private final String NS = "kr.co.member";
	

	@Override
	public MemberVO read(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+".read", id);
	}
	
	@Override
	public MemberVO readM(int mnum) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+".readM", mnum);
	}


	@Override
	public int insert(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return sqlSession.insert(NS+".insert", memberVO);
	}


	@Override
	public MemberVO login(LoginDTO loginDTO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+".login", loginDTO);
	}


	@Override
	public void update(MemberVO vo) {
		sqlSession.update(NS+".update", vo);
		
	}


	@Override
	public int updatePoint(int point, String memo, int mnum) {
		MemberVO vo = new MemberVO();
		vo.setPoint(point);
		vo.setMemo(memo);
		vo.setMnum(mnum);
		return sqlSession.update(NS+".updatePoint", vo);
	}


	@Override
	public void updateMType(MemberVO vo) {
		sqlSession.update(NS+".updateMType", vo);
		
	}

}
