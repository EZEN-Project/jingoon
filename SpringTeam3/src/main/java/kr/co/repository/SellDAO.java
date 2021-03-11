package kr.co.repository;

import kr.co.domain.SellVO;

public interface SellDAO {
	
	Integer getMaxGroupNum();

	void insert(SellVO sellVO);	

}
