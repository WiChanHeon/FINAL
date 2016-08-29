package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import kr.spring.board.domain.BoardCommand;
import kr.spring.board.domain.BoardReplyCommand;

@Repository
public interface BoardMapper {
	//부모글
	public List<BoardCommand> list(Map<String,Object> map);
	public int getRowCount(Map<String,Object> map);
	@Insert("INSERT INTO sboard (seq,title,content,regdate,filename,id) VALUES (board_seq.nextval,#{title},#{content},sysdate,#{filename,jdbcType=VARCHAR},#{id})")
	public void insert (BoardCommand board);
	@Select("SELECT * FROM sboard WHERE seq = #{seq}")
	public BoardCommand selectBoard(Integer seq);
	@Update("UPDATE sboard SET hit=hit+1 WHERE seq = #{seq}")
	public void updatehit(Integer seq);
	@Update("UPDATE sboard SET title=#{title},content=#{content},filename=#{filename,jdbcType=VARCHAR} WHERE seq = #{seq}")
	public void update(BoardCommand board);
	@Delete("DELETE FROM sboard WHERE seq= #{seq}")
	public void delete(Integer seq);
	//댓글
	public List<BoardReplyCommand> listReply(Map<String,Object> map);
	@Select("SELECT count(*) FROM sreply WHERE seq = #{seq}")
	public int getRowCountReply(Map<String,Object> map);
	@Insert("INSERT INTO sreply (re_no,re_content,re_date,seq,id) VALUES (re_seq.nextval,#{re_content},sysdate,#{seq},#{id})")
	public void insertReply (BoardReplyCommand boardReply);
	@Update("UPDATE sreply SET re_content = #{re_content} WHERE re_no = #{re_no}")
	public void updateReply(BoardReplyCommand boardReply);
	@Delete("DELETE FROM sreply WHERE re_no= #{re_no}")
	public void deleteReply(Integer re_no);
	
	//부모글에 댓글이 있으면 부모글 삭제시 댓글을 먼저 삭제해야 함. 부모글의 글번호로 댓글 삭제
	@Delete("DELETE FROM sreply WHERE seq= #{seq}")
	public void deleteReplyBySeq(Integer seq);
	//회원탈퇴시 처리사항, 해당 id로 작성된 모든글의 글번호 구하기
	@Select("SELECT seq FROM sboard where id=#{id}")
	public List<Integer> selectSeqById(String id);
	//해당 id로 작성된 모든 글에 달린 댓글을 삭제
	public void deleteReplyBySeqList(List<Integer> list);
	//해당 id로 작성된 모든 댓글 삭제
	@Delete("DELETE FROM sreply WHERE id= #{id}")
	public void deleteReplyById(String id);
	//해당 id로 작성된 모든 부모글 삭제
	@Delete("DELETE FROM sboard WHERE id= #{id}")
	public void deleteById(String id);
}
