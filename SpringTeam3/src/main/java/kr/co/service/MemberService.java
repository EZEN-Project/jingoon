package kr.co.service;

import kr.co.domain.LoginDTO;
import kr.co.domain.MemberVO;

public interface MemberService {

	String idcheck(String id);

	int insert(MemberVO memberVO);

	MemberVO login(LoginDTO loginDTO);

	MemberVO read(String id);

	void update(MemberVO vo);

	void updateMType(MemberVO vo);

	int addPoint(int point, int mnum, String memo);

	int getPoint(int mnum);
}
