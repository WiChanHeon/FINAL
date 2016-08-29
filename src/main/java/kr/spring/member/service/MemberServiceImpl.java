package kr.spring.member.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.dao.BoardMapper;
import kr.spring.member.dao.MemberMapper;
import kr.spring.member.domain.MemberCommand;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Resource
	private MemberMapper memberMapper;
	
	@Resource
	private BoardMapper boardMapper;
	
	@Override
	public void insert(MemberCommand memberCommand) {
		memberMapper.insert(memberCommand);
		
	}

	@Override
	public MemberCommand selectMember(String id) {
		return memberMapper.selectMember(id);  
	}

	@Override
	public void update(MemberCommand member) {
		memberMapper.update(member);
		
	}

	@Override
	public void delete(String id) {
		//해당 아이디로 작성된 부모글의 글 번호 구하기
		List<Integer> seq = boardMapper.selectSeqById(id);
		//해당 아이디로 작성된 부모글으 달린 댓글 삭제
		if(seq!=null && !seq.isEmpty()){
			boardMapper.deleteReplyBySeqList(seq);
		}
		//해당 아이디로 작성된 댓글 삭제
		boardMapper.deleteReplyById(id);
		//해당 아이디로 작성된 부모글 삭제
		boardMapper.deleteById(id);
		//해당 아이디 삭제
		memberMapper.delete(id);
	}
	
}
