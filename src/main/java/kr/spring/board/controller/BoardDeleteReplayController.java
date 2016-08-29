package kr.spring.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.board.service.BoardService;

@Controller
public class BoardDeleteReplayController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private BoardService boardService;
	
	@RequestMapping("/board/deleteReplyAjax.do")
	@ResponseBody
	public Map<String,String> process(@RequestParam("re_no") int re_no,@RequestParam("id") String id,HttpSession session){
		if(log.isDebugEnabled()){
			log.debug("re_no : " + re_no);
			log.debug("id : "+ id);
		}
		Map<String,String> map = new HashMap<String,String>();
		
		try {
			String userId = (String)session.getAttribute("userId");
			if(userId==null){
				map.put("result", "logout");
			}else if(userId!=null && userId.equals(id)){
				//¥Ò±€ ªË¡¶
				boardService.deleteReply(re_no);
				map.put("result", "success");
			}else{
				map.put("result", "wrongAccess");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("reuslt", "failure");
		}
		return map;
	}
}
