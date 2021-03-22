package kr.co.service;

import java.util.List;

import kr.co.domain.QnABoardVO;

public interface QnABoardService {

	List<QnABoardVO> list(int curPage);

	int getAmount();

	void insert(QnABoardVO vo);

	void delete(int bnum);

	QnABoardVO read(int bnum);

	QnABoardVO updateUI(int bnum);

	void update(QnABoardVO vo);


}
