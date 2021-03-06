package kr.spring.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.spring.board.domain.BoardReplyCommand;
import kr.spring.board.service.BoardService;

@Controller
@SessionAttributes("command")
public class BoardWriteReplayController {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private BoardService boardService;

	@RequestMapping("/board/writeReplyAjax.do")
	@ResponseBody
	public Map<String,String> process(BoardReplyCommand boardReplyCommand,HttpSession session){
		
		if(log.isDebugEnabled()){
			log.debug("boardReplyCommand : "+ boardReplyCommand);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		
		try {
			String userId = (String)session.getAttribute("userId");
			if(userId == null){//�α��� �ȵ�
				map.put("result", "logout");
			}else{//�α��ε�
				//��� ���
				boardService.insertReply(boardReplyCommand);
				map.put("result", "success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "failure");
		}
		
		return map;
	}

}
