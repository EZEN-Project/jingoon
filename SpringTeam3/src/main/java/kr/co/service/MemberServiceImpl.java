package kr.co.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.domain.LoginDTO;
import kr.co.domain.MemberVO;
import kr.co.repository.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService{

	@Inject
	private MemberDAO memberDAO;
	
	@Override
	public String idcheck(String id) {
		MemberVO memberVO= memberDAO.read(id);
		if(memberVO == null) {
			return "o"; 
		}else {
			return "x";
		}
		
	}

	@Override
	public int insert(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return memberDAO.insert(memberVO);
	}

	@Override
	public MemberVO login(LoginDTO loginDTO) {
		// TODO Auto-generated method stub
		return memberDAO.login(loginDTO);
	}

	@Override
	public MemberVO read(MemberVO vo) {
		// TODO Auto-generated method stub
		return memberDAO.read(vo.getId());
	}

	@Override
	public void update(MemberVO vo) {
		vo.setmType(1);
		memberDAO.update(vo);
	}

	@Override
	public void updateD(MemberVO vo) {
		vo.setmType(2);
		memberDAO.update(vo);
	}

}
