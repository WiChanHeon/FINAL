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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.spring.board.domain.BoardCommand;
import kr.spring.board.service.BoardService;
import kr.spring.util.FileUtil;

@Controller
@SessionAttributes("command")//글번호를 자바빈에 보관하고 자바빈에있는 데이터를 세션에 보관
public class BoardUpdateController {
	//로그 처리
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private BoardService boardService;
	
	@RequestMapping(value="/board/update.do",method=RequestMethod.GET)
	public String form(@RequestParam("seq") int seq,Model model){
		
		BoardCommand boardCommand = boardService.selectBoard(seq);
		model.addAttribute("command", boardCommand);
		
		return "boardModify";
	}
	@RequestMapping(value="/board/update.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command")
						 @Valid BoardCommand boardCommand,
						 BindingResult result,
						 SessionStatus status,
						 HttpSession session) throws Exception{	
		
		if(log.isDebugEnabled()){
			log.debug("boardCommand : " + boardCommand );
		}
		
		String userId = (String)session.getAttribute("userId");
		if(!userId.equals(boardCommand.getId())){
			throw new Exception("로그인한 아이디로 작성된 글이 아니여유");
		}
		
		if(result.hasErrors()){
			return "boardModify";
		}
		
		BoardCommand board = boardService.selectBoard(boardCommand.getSeq());
		String oldFileName = "";
		
		//기존 파일명을 구함
		//업로드되는 파일이 있을 경우 기존 파일을 삭제 새로운 파일명 셋팅 업로되는 파일이 없을경우 기존 파일명을 셋팅
		oldFileName = board.getFilename();
		
		if(!boardCommand.getUpload().isEmpty()){
			//전송된 파일이 있을 경우
			boardCommand.setFilename(FileUtil.rename(boardCommand.getUpload().getOriginalFilename()));
		}else{
			//전송된 파일이 없을 경우
			boardCommand.setFilename(oldFileName);
		}
		
		//글 수정
		boardService.update(boardCommand);
		status.setComplete();
		
		if(!boardCommand.getUpload().isEmpty()){
			//전송된 파일이 있을 경우
			File file = new File(FileUtil.UPLOAD_PATH+"/"+boardCommand.getFilename());
			boardCommand.getUpload().transferTo(file);
			
			if(oldFileName!= null){
				//이전 파일 삭제
				FileUtil.removeFile(oldFileName);
			}
		}
		return "redirect:/board/list.do";
	}
}
