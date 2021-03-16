package kr.co.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {
	
	@Inject
	private MemberService memberService;

	@RequestMapping(value = "/idcheck/{id}", method = RequestMethod.GET)
	public Map<String, String> idcheck(@PathVariable("id") String id) {
		String result = memberService.idcheck(id);
	
		Map<String, String> map = new HashMap<>();
		map.put("result", result);
		return map;
	}
}
