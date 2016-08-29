package kr.spring.board.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.spring.board.domain.BoardCommand;
import kr.spring.board.service.BoardService;
import kr.spring.util.FileUtil;

@Controller
@SessionAttributes("command")
public class BoardDeleteController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private BoardService boardService;
	
	@RequestMapping("/board/delete.do")
	public String submit(@RequestParam("seq") int seq,HttpSession session) throws Exception{
		
		if(log.isDebugEnabled()){
			log.debug("seq : "+seq);
		}
		
		BoardCommand boardCommand = boardService.selectBoard(seq);
		
		String userId = (String)session.getAttribute("userId");
		
		if(!userId.equals(boardCommand.getId())){
			throw new Exception("로그인한 아이디로 작성된 글이 아니여유");
		}
		
		//글 삭제
		boardService.delete(boardCommand.getSeq());
		
		//파일 삭제 여부 체크
		if(boardCommand.getFilename()!=null){
			FileUtil.removeFile(boardCommand.getFilename());
		}
		
		return "redirect:/board/list.do";
	}
	
}
