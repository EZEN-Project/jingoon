package kr.co.repository;

import java.util.List;
import java.util.Map;

import kr.co.domain.PageTO;
import kr.co.domain.ReplyVO;

public interface ReplyDAO {

	void insert(ReplyVO vo);

	int update(Map<String, Object> map);

	int delete(Map<String, Object> map);

	int getReplyAmount(int bnum);

	List<ReplyVO> list(int bnum, PageTO<ReplyVO> to);
	void deleteReplies(int bnum);
}
