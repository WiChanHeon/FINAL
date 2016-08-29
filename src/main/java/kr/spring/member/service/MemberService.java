package kr.spring.member.service;

import org.springframework.transaction.annotation.Transactional;
import kr.spring.member.domain.MemberCommand;
@Transactional
public interface MemberService {
	public void insert(MemberCommand memberCommand);
	@Transactional(readOnly=true)
	public MemberCommand selectMember(String id);
	public void update(MemberCommand member);
	public void delete(String id);
}
