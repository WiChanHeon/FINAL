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
 * @SessionAttributes 명시하면 자바빈을 자동으로 세션에 저장.세션에 자바빈을 저장하게 되면 폼에서 전송된 데이터를 세션에 저장된
 * 자바빈에 셋팅함.이렇게 처리하면 id를 hidden 처리하지 않아도 session에 저장된 자바빈에 id 보관되어 있기때문에 자바빈에
 * 데이터를 셋팅시 id도 함께 사용 가능함.또한 업데이트 처리시 session에서 자바빈을 제거하기때문에 Double submit 방지가능
 */
@SessionAttributes("command")
public class MemberUpdateController {
	//로그 처리
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private MemberService memberService;
	
	@RequestMapping(value="/member/update.do",method=RequestMethod.GET)
	public String form(HttpSession session,Model model){
		
		String id = (String)session.getAttribute("userId");
		MemberCommand memberCommand = memberService.selectMember(id);
		//모델에 저장된 정보는 @SessionAttributes("command")통해 세션에 저장
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
		//유효성 체크
		if(result.hasErrors()){
			return "memberModify";
		}
		
		memberService.update(memberCommand);
		//session에 저장된 model을 삭제하는 이벤트 발생
		status.setComplete();
		
		return "redirect:/member/detail.do";  
	}
}
