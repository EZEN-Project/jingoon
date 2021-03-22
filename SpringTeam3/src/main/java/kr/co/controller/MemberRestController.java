package kr.co.controller;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.domain.MemberVO;
import kr.co.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberRestController {
	
	@Inject
	private MemberService memberService;
	//비밀번호 변경
	@RequestMapping(value = "/update_pw", method = RequestMethod.PUT)
	public void updatepw(@RequestBody Map<String, Object> map) {
	
		memberService.updatepw(map);
		
	}
	
	// id중복검사
	@RequestMapping(value = "/idcheck/{id}", method = RequestMethod.GET)
	public Map<String, String> idcheck(@PathVariable("id") String id) {
		String result = memberService.idcheck(id);
	
		Map<String, String> map = new HashMap<>();
		map.put("result", result);
		return map;
	}
	
	// 포인트 충전
	@RequestMapping(value = "/addPoint/{point}", method = RequestMethod.GET)
	public Map<String, Integer> addPoint(@PathVariable("point") int point, HttpSession session) {
		Map<String, Integer> map = new HashMap<>();
		
		MemberVO vo = (MemberVO) session.getAttribute("login");
		int mnum = vo.getMnum();
		String memo = "포인트 충전";
		int successNpoint = memberService.addPoint(point, mnum, memo);
		
		map.put("point", successNpoint);
		return map;
	}
	
	// 현재 포인트 반환
	@RequestMapping(value = "/getPoint", method = RequestMethod.GET)
	public Map<String, Integer> getPoint(HttpSession session) {
		
		MemberVO vo = (MemberVO) session.getAttribute("login");
		int mnum = vo.getMnum();
		int point = memberService.getPoint(mnum);
		Map<String, Integer> map = new HashMap<>();
		map.put("point", point);
		return map;
	}
	
}
