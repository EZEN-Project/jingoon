package kr.co.repository;

import java.util.List;
import java.util.Map;

import kr.co.domain.QnABoardVO;

public interface QnABoardDAO {

	List<QnABoardVO> list(int curPage);

	int getAmount();

	void insert(QnABoardVO vo);

	void delete(int bnum);

	QnABoardVO read(int bnum);

	void updateReplyCnt(int bnum);

	void updateReplyCntMinus(Map<String, Object> map);
	
	void updateViewCnt(int bnum);

	QnABoardVO updateUI(int bnum);

	void update(QnABoardVO vo);


}
