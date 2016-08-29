package w.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import kr.spring.board.domain.BoardCommand;
import kr.spring.board.domain.BoardReplyCommand;

@Transactional
public interface DonanBoardService {
	//ºÎ¸ð±Û
	@Transactional(readOnly=true)
	public List<BoardCommand> list(Map<String,Object> map);
	@Transactional(readOnly=true)
	public int getRowCount(Map<String,Object> map);
	public void insert (BoardCommand board);
	@Transactional(readOnly=true)
	public BoardCommand selectBoard(Integer seq);
	public void updatehit(Integer seq);
	public void update(BoardCommand board);
	public void delete(Integer seq);

	//´ñ±Û
	@Transactional(readOnly=true)
	public List<BoardReplyCommand> listReply(Map<String,Object> map);
	@Transactional(readOnly=true)
	public int getRowCountReply(Map<String,Object> map);
	public void insertReply (BoardReplyCommand boardReply);
	public void updateReply(BoardReplyCommand boardReply);
	public void deleteReply(Integer re_no);
}
