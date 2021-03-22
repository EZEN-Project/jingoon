package kr.co.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.domain.MemberVO;
import kr.co.domain.SellVO;
import kr.co.domain.TotalVO;
import kr.co.service.MyPageService;

@RestController
@RequestMapping("/mypage")
public class RestMyPageController {
	@Inject
	private MyPageService service;

	// 일일 매출차트
	@RequestMapping(value = "/sales/chart_day", method = RequestMethod.GET)
	public List<TotalVO> chart_day() {

		List<TotalVO> vo = service.chart_day();

		return vo;
	}

	// 월별 매출차트
	@RequestMapping(value = "/sales/chart_month", method = RequestMethod.GET)
	public List<TotalVO> chart_month() {

		List<TotalVO> vo = service.chart_month();

		return vo;
	}

	// 연별 매출차트
	@RequestMapping(value = "/sales/chart_year", method = RequestMethod.GET)
	public List<TotalVO> chart_year() {

		List<TotalVO> vo = service.chart_year();

		return vo;
	}

	// 결제내역 리스트
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<SellVO> list(HttpSession session) {

		MemberVO membervo = (MemberVO) session.getAttribute("login");
		List<SellVO> vo = service.list(membervo.getMnum());

		return vo;
	}
	// 결제내역 삭제(그룹넘버를 -1로 수정하며 웹상에서만 삭제, DB에서는 기록 보존)

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public String delete(@RequestBody Map<String, Object> map) {

		service.delete(map);

		return "o";
	}

	// 결제내역 삭제(그룹넘버를 -1로 수정하며 웹상에서만 삭제, DB에서는 기록 보존)
	@RequestMapping(value = "/allDelete", method = RequestMethod.GET)
	public Map<String, Object> allDelete(HttpSession session) {
		MemberVO membervo = (MemberVO) session.getAttribute("login");

		service.allDelte(membervo.getMnum());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("o", "o");

		return map;
	}

}
