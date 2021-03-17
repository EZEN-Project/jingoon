package kr.co.service;

import java.util.List;

import kr.co.domain.SearchPageTO;
import kr.co.domain.SellVO;

public interface SMyPageService {

	Integer getAmount(SearchPageTO<SellVO> spt);

	List<SellVO> adminlist(SearchPageTO<SellVO> spt);

}
