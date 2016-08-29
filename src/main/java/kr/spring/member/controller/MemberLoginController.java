package kr.spring.member.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;

@Controller
public class MemberLoginController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private MemberService memberService;
	
	//Ŀ�ǵ� ��ü �ʱ�ȭ
	@ModelAttribute("command")
	public MemberCommand initCommand(){
		return new MemberCommand();
	}
	
	@RequestMapping(value="/member/login.do", method=RequestMethod.GET)
	public String form(){
		return "memberLogin";
	}
	@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command")
						 @Valid MemberCommand memberCommand,
						 BindingResult result,
						 HttpSession session){
		
		if(log.isDebugEnabled()){
			log.debug("memberCommand : " + memberCommand);
		}
		//��ȿ�� üũ
		if(result.hasFieldErrors("id") || result.hasFieldErrors("passwd")){
			
			return form();
		}
		
		//�α���üũ
		try {
			MemberCommand member = memberService.selectMember(memberCommand.getId());
			
			if(log.isDebugEnabled()){
				log.debug("member : " + member);
			}
			
			boolean check = false;
			
			if(member!=null){
				check = member.isCheckedPasswd(memberCommand.getPasswd());
			}
			
			if(check){
				//���� ����
				session.setAttribute("userId", memberCommand.getId());
				return "redirect:/main/main.do";
			}else{
				//���� ����
				throw new Exception();
			}
		} catch (Exception e) {
			result.reject("invalidIdOrPassword");
			return form();
		}
	}  
}
