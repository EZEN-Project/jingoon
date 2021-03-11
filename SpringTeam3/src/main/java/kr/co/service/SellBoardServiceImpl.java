package kr.co.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.domain.SellBoardVO;
import kr.co.repository.SellBoardDAO;

@Service
public class SellBoardServiceImpl implements SellBoardService{

	@Inject
	private SellBoardDAO sellboardDao;
	
	@Override
	public void insert(SellBoardVO vo) {
		sellboardDao.insert(vo);
		
		String[] files = vo.getFiles();
		if (files == null) {
			return;
		}
		
		for (String fullName : files) {
			sellboardDao.addAttach(fullName, vo.getBnum());
		}
	}

	@Override
	public SellBoardVO read(int bnum) {
		
		return sellboardDao.read(bnum);
	}

	@Override
	public List<String> getAttaches(int bnum) {
		// TODO Auto-generated method stub
		return sellboardDao.getAttaches(bnum);
	}

	@Override
	public SellBoardVO updateUI(int bnum) {
		// TODO Auto-generated method stub
		return sellboardDao.updateUI(bnum);
	}

	@Override
	public void update(SellBoardVO vo) {
		sellboardDao.update(vo);
		
	}

	@Override
	public void delete(int bnum) {
		sellboardDao.delete(bnum);
		
	}

}
