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
		//�ش� ���̵�� �ۼ��� �θ���� �� ��ȣ ���ϱ�
		List<Integer> seq = boardMapper.selectSeqById(id);
		//�ش� ���̵�� �ۼ��� �θ���� �޸� ��� ����
		if(seq!=null && !seq.isEmpty()){
			boardMapper.deleteReplyBySeqList(seq);
		}
		//�ش� ���̵�� �ۼ��� ��� ����
		boardMapper.deleteReplyById(id);
		//�ش� ���̵�� �ۼ��� �θ�� ����
		boardMapper.deleteById(id);
		//�ش� ���̵� ����
		memberMapper.delete(id);
	}
	
}
