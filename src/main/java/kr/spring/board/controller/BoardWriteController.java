package kr.spring.board.controller;

import java.io.File;

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

import kr.spring.board.domain.BoardCommand;
import kr.spring.board.service.BoardService;
import kr.spring.util.FileUtil;

@Controller
@SessionAttributes("command")
public class BoardWriteController {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private BoardService boardService;

	@RequestMapping(value="/board/write.do",method=RequestMethod.GET)
	public String form(HttpSession session, Model model){
		String id = (String)session.getAttribute("userId");

		BoardCommand command = new BoardCommand();
		command.setId(id);

		model.addAttribute("command",command);

		return "boardWrite";
	}
	@RequestMapping(value="/board/write.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command")
						 @Valid BoardCommand boardCommand,
						 BindingResult result,
						 SessionStatus status) throws Exception{

		if(log.isDebugEnabled()){
			log.debug("boardCommand : " + boardCommand );
		}

		//유효성 체크
		if(result.hasErrors()){
			return "boardWrite";
		}

		String newName = "";
		if(!boardCommand.getUpload().isEmpty()){
			newName = FileUtil.rename(boardCommand.getUpload().getOriginalFilename());
			boardCommand.setFilename(newName);
		}
		
		//글 등록
		boardService.insert(boardCommand);
		status.setComplete();
		
		if(!boardCommand.getUpload().isEmpty()){
			File file = new File(FileUtil.UPLOAD_PATH+"/"+newName);
			boardCommand.getUpload().transferTo(file);
		}
		
		return "redirect:/board/list.do";
	}

}
