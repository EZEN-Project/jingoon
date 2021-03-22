package kr.co.service;

import java.util.List;

import kr.co.domain.QnABoardVO;
import kr.co.domain.SearchPageTO;

public interface SQABoardService {

	Integer getAmount(SearchPageTO<QnABoardVO> spt);



	List<QnABoardVO> list(SearchPageTO<QnABoardVO> spt);

}
