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
	public MemberVO read(String id) {
		// TODO Auto-generated method stub
		return memberDAO.read(id);
	}

	@Override
	public void update(MemberVO vo) {
		memberDAO.update(vo);
	}

	@Override
	public void updateMType(MemberVO vo) {
		memberDAO.update(vo);
	}

	@Override
	public int addPoint(int point, int mnum, String memo) {
		MemberVO vo = memberDAO.readM(mnum);
		int points = point + vo.getPoint();
		int success= memberDAO.updatePoint(points, memo, mnum);
		if(success ==1) {
			return points;
		}
		return success;
	}

	@Override
	public int getPoint(int mnum) {
		MemberVO vo = memberDAO.readM(mnum);
		return vo.getPoint();
	}

}
