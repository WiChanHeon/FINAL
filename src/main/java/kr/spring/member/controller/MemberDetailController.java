package kr.spring.member.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;

@Controller
public class MemberDetailController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private MemberService memberService;
	
	@RequestMapping("/member/detail.do")
	public ModelAndView process(HttpSession session){
		
		String id = (String)session.getAttribute("userId");
		
		if(log.isDebugEnabled()){
			log.debug("id : " + id);
		}
		
		MemberCommand member = memberService.selectMember(id);
								//뷰이름		  속성명		속성값
		return new ModelAndView("memberView","member",member);
	}
}
  