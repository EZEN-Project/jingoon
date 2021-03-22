package kr.co.service;

import java.io.PrintWriter;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;
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
		int success =-1;
		int points = point + vo.getPoint();
		if(points<0 || points >200000000 || point<=0) {
			return -2;
		}
		success= memberDAO.updatePoint(points, memo, mnum);
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

	@Override
	public void sendEmail(MemberVO vo, String div) throws Exception {
		// Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.naver.com"; //네이버 이용시 smtp.naver.com
		String hostSMTPid = "rmh63@naver.com";//서버 이메일 주소(보내는 사람 이메일 주소)
		String hostSMTPpwd = "@@min960904";//서버 이메일 비번(보내는 사람 이메일 비번)

		// 보내는 사람 EMail, 제목, 내용
		String fromEmail = "rmh63@naver.com";//보내는 사람 이메일주소(받는 사람 이메일에 표시됨)
		String fromName = "Team-Ezen";//프로젝트이름 또는 보내는 사람 이름
		String subject = "";
		String msg = "";

		if(div.equals("findpw")) {
			subject = "임시 비밀번호 입니다.";
			msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
			msg += "<h3 style='color: blue;'>";
			msg += vo.getId() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
			msg += "<p>임시 비밀번호 : ";
			msg += vo.getPw() + "</p></div>";
		}

		// 받는 사람 E-Mail 주소
		String mail = vo.getEmail();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(587); //네이버 이용시 587 구글 이용시465

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
		}
	}

	//비밀번호찾기

	@Override
	public void tempPw(HttpServletResponse response, MemberVO vo) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		MemberVO ck = memberDAO.readMember(vo.getId());
		PrintWriter out = response.getWriter();
		// 가입된 아이디가 없으면
		if(memberDAO.read(vo.getId()) == null) {
			out.print("등록되지 않은 아이디입니다.");
			out.close();
		}
		// 가입된 이메일이 아니면
		else if(!vo.getEmail().equals(ck.getEmail())) {
			out.print("등록되지 않은 이메일입니다.");
			out.close();
		}else {
			// 임시 비밀번호 생성
			String pw = "";
			for (int i = 0; i < 12; i++) {
				pw += (char) ((Math.random() * 26) + 97);
			}
			vo.setPw(pw);
			// 비밀번호 변경
			memberDAO.tempPw(vo);
			// 비밀번호 변경 메일 발송
			sendEmail(vo, "findpw");

			out.print("이메일로 임시 비밀번호를 발송하였습니다.");
			out.close();
		}

}

	@Override
	public MemberVO readMember(String id) {
		// TODO Auto-generated method stub
		System.out.println("S : readMember()실행");
		MemberVO vo = null;
		
		try {
			vo = memberDAO.readMember(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}

	@Override
	public String findid(HttpServletResponse response, String email) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = memberDAO.findid(email);
		
		if (id == null) {
			out.println("<script>");
			out.println("alert('가입된 아이디가 없습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			return null;
		} else {
			return id;
		}
	}

	@Override
	public void updatepw(Map<String, Object> map) {
		memberDAO.updatepw(map);
		
	}
}
