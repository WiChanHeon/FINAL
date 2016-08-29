package kr.spring.member.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;

@Controller
/*
 * @SessionAttributes ����ϸ� �ڹٺ��� �ڵ����� ���ǿ� ����.���ǿ� �ڹٺ��� �����ϰ� �Ǹ� ������ ���۵� �����͸� ���ǿ� �����
 * �ڹٺ� ������.�̷��� ó���ϸ� id�� hidden ó������ �ʾƵ� session�� ����� �ڹٺ� id �����Ǿ� �ֱ⶧���� �ڹٺ�
 * �����͸� ���ý� id�� �Բ� ��� ������.���� ������Ʈ ó���� session���� �ڹٺ��� �����ϱ⶧���� Double submit ��������
 */
@SessionAttributes("command")
public class MemberUpdateController {
	//�α� ó��
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private MemberService memberService;
	
	@RequestMapping(value="/member/update.do",method=RequestMethod.GET)
	public String form(HttpSession session,Model model){
		
		String id = (String)session.getAttribute("userId");
		MemberCommand memberCommand = memberService.selectMember(id);
		//�𵨿� ����� ������ @SessionAttributes("command")���� ���ǿ� ����
		model.addAttribute("command", memberCommand);
		
		return "memberModify";
	}
	@RequestMapping(value="/member/update.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command")
						 @Valid MemberCommand memberCommand,
						 BindingResult result,
						 SessionStatus status){
		
		if(log.isDebugEnabled()){
			log.debug("memberCommand : " + memberCommand);
		}
		//��ȿ�� üũ
		if(result.hasErrors()){
			return "memberModify";
		}
		
		memberService.update(memberCommand);
		//session�� ����� model�� �����ϴ� �̺�Ʈ �߻�
		status.setComplete();
		
		return "redirect:/member/detail.do";  
	}
}
