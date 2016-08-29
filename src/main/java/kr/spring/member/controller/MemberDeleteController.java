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
@SessionAttributes("command")
public class MemberDeleteController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private MemberService memberService;
	
	@RequestMapping(value="/member/delete.do",method=RequestMethod.GET)
	public String form(HttpSession session,Model model){
		
		String id = (String)session.getAttribute("userId");
		MemberCommand memberCommand = new MemberCommand();
		memberCommand.setId(id);
		
		model.addAttribute("command", memberCommand);
		
		return "memberDelete";
	}
	@RequestMapping(value="/member/delete.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command")
						 @Valid MemberCommand memberCommand,
						 BindingResult result,
						 SessionStatus status,
						 HttpSession session){
		
		if(log.isDebugEnabled()){
			log.debug("memberCommand : "  + memberCommand);
		}
		
		//passwd 필드의 에러만 체크
		if(result.hasFieldErrors("passwd")){
			return "memberDelete";
		}
		
		try {
			MemberCommand member = memberService.selectMember(memberCommand.getId());
			boolean check = false;
			if(member!=null){
				check = member.isCheckedPasswd(memberCommand.getPasswd());
			}
			if(check){
				//인증 성공
				memberService.delete(memberCommand.getId());
				status.setComplete();
				//로그아웃ㅎ
				session.invalidate();
				return "redirect:/main/main.do";
			}else{
				//인증실패
				throw new Exception();
			}
		} catch (Exception e) {
			result.rejectValue("passwd", "invalidPassword");
			return "memberDelete";
		}
		
	} 
}
