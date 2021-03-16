package kr.co.repository;

import kr.co.domain.LoginDTO;
import kr.co.domain.MemberVO;

public interface MemberDAO {
	
	MemberVO read(String id);

	int insert(MemberVO memberVO);

	MemberVO login(LoginDTO loginDTO);

	void update(MemberVO vo);

}
