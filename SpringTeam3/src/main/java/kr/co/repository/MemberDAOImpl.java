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

}
