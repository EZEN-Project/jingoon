package kr.co.service;

import kr.co.domain.LoginDTO;
import kr.co.domain.MemberVO;

public interface MemberService {

	String idcheck(String id);

	int insert(MemberVO memberVO);

	MemberVO login(LoginDTO loginDTO);

	MemberVO read(MemberVO vo);

	void update(MemberVO vo);

	void updateD(MemberVO vo);
}
