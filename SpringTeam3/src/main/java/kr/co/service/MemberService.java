package kr.co.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
	void sendEmail(MemberVO vo, String div) throws Exception;
	void tempPw(HttpServletResponse response, MemberVO member)throws Exception;
	MemberVO readMember(String id);
	String findid(HttpServletResponse response, String email) throws Exception;

	void updatepw(Map<String, Object> map);
}
