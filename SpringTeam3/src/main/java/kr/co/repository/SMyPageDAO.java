package kr.co.repository;

import java.util.List;

import kr.co.domain.SearchPageTO;
import kr.co.domain.SellVO;

public interface SMyPageDAO {

	Integer getAmount(SearchPageTO<SellVO> spt);

	List<SellVO> adminlist(SearchPageTO<SellVO> spt);

}
