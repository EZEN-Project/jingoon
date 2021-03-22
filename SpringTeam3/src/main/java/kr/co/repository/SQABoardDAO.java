package kr.co.repository;

import java.util.List;

import kr.co.domain.QnABoardVO;
import kr.co.domain.SearchPageTO;

public interface SQABoardDAO {

	Integer getAmount(SearchPageTO<QnABoardVO> spt);

	List<QnABoardVO> list(SearchPageTO<QnABoardVO> spt);

}
