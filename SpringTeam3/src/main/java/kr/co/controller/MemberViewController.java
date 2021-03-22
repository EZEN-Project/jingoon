package kr.co.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.domain.LoginDTO;
import kr.co.domain.MemberVO;
import kr.co.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberViewController {
	
	@Autowired
	private MemberService memberService;
	
	
	
	
	
	// 아이디 찾기
	@RequestMapping(value = "/findid", method = RequestMethod.POST)
	public String findIdGET(HttpServletResponse response, @RequestParam
			("email") String email, Model md) throws Exception{
		md.addAttribute("id", memberService.findid(response, email));
		return "/member/findid_result";
				
	}
	@RequestMapping(value = "/findid",method = RequestMethod.GET)
	public void findIdGET() throws Exception{
		
	}
	@RequestMapping(value = "/findpw", method = RequestMethod.GET)
	public void findPwGET() throws Exception{
	}

	@RequestMapping(value = "/findpw", method = RequestMethod.POST)
	public void findPwPOST(@ModelAttribute MemberVO member, HttpServletResponse response) throws Exception{
		memberService.tempPw(response, member);
	}

	//회원가입
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insert() {
		
	}
	
	//회원가입
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(MemberVO memberVO) {
		int success = memberService.insert(memberVO);
		if(success==1) {
			return "/member/login";
		}else {
			return "/member/insert";
		}
	}
	
	// 회원 보기
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(Model model,HttpSession session) {
		MemberVO vo= (MemberVO) session.getAttribute("login");
		MemberVO memberVO = memberService.read(vo.getId());
		model.addAttribute("memberVO", memberVO);
	}
	
	// 회원 수정
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void update(Model model,HttpSession session) {
		MemberVO vo= (MemberVO) session.getAttribute("login");
		MemberVO memberVO = memberService.read(vo.getId());
		model.addAttribute("memberVO", memberVO);
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(MemberVO vo) {
		memberService.update(vo);
		return "redirect:/member/read";
	}
	
	// 회원 탈퇴 mnum, mType, memo 
	@RequestMapping(value = "/updateD", method = RequestMethod.POST)
	public String updateD(MemberVO vo) {
		vo.setMemo("회원탈퇴");
		vo.setmType(2);
		memberService.updateMType(vo);
		return "redirect:/member/logout";
	}
	
	
	//로그인
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login(HttpServletRequest request) {
		// 로그인 전에 있던 페이지 세션에 저장
		String referer =request.getHeader("referer");
		System.out.println("referer "+referer);//////
		HttpSession session = request.getSession();
		session.setAttribute("referer", referer);
	}
	//로그인
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public String loginPost(LoginDTO loginDTO, Model model) {
		MemberVO login= memberService.login(loginDTO);
		// 탈퇴회원 로그인 막기
		if(login!=null&&login.getmType() == 2) {	
			return "/member/login";
		}else {
			model.addAttribute("login", login);
			return "/member/loginPost";
		}
		
	}
	
	//로그아웃
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {

		// 로그인 되어있다면 로그아웃 시킴
		Object login= session.getAttribute("login");
		 if(login != null) {
			 session.removeAttribute("login"); 
		 }
		 return "redirect:/";
	}
	
	
	
	
	
	
}
