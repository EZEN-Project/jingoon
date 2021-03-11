package kr.co.service;

import java.util.List;

import kr.co.domain.SellBoardVO;

public interface SellBoardService {

	public void insert(SellBoardVO vo);

	public SellBoardVO read(int bnum);

	public List<String> getAttaches(int bnum);

	public SellBoardVO updateUI(int bnum);

	public void update(SellBoardVO vo);

	public void delete(int bnum);

}
