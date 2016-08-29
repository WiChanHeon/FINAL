package kr.spring.board.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.dao.BoardMapper;
import kr.spring.board.domain.BoardCommand;
import kr.spring.board.domain.BoardReplyCommand;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	@Resource
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardCommand> list(Map<String, Object> map) {
		
		return boardMapper.list(map);
	}

	@Override
	public int getRowCount(Map<String, Object> map) {
		return boardMapper.getRowCount(map);
	}

	@Override
	public void insert(BoardCommand board) {
		boardMapper.insert(board);
		
	}

	@Override
	public BoardCommand selectBoard(Integer seq) {
		return boardMapper.selectBoard(seq);
	}

	@Override
	public void updatehit(Integer seq) {
		boardMapper.updatehit(seq);
		
	}

	@Override
	public void update(BoardCommand board) {
		boardMapper.update(board);
		
	}

	@Override
	public void delete(Integer seq) {
		//댓글이 존재하면 댓글을 우선 삭제하고 부모글을 지움
		//댓글 삭제
		boardMapper.deleteReplyBySeq(seq);
		//부모글 삭제
		boardMapper.delete(seq);
		
	}

	@Override
	public List<BoardReplyCommand> listReply(Map<String, Object> map) {
		return boardMapper.listReply(map);
	}

	@Override
	public int getRowCountReply(Map<String, Object> map) {
		return boardMapper.getRowCountReply(map);
	}

	@Override
	public void insertReply(BoardReplyCommand boardReply) {
		boardMapper.insertReply(boardReply);
		
	}

	@Override
	public void updateReply(BoardReplyCommand boardReply) {
		boardMapper.updateReply(boardReply);
		
	}

	@Override
	public void deleteReply(Integer re_no) {
		boardMapper.deleteReply(re_no);
	}
	
	
}
