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
@SessionAttributes("command")//�۹�ȣ�� �ڹٺ� �����ϰ� �ڹٺ��ִ� �����͸� ���ǿ� ����
public class BoardUpdateController {
	//�α� ó��
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
			throw new Exception("�α����� ���̵�� �ۼ��� ���� �ƴϿ���");
		}
		
		if(result.hasErrors()){
			return "boardModify";
		}
		
		BoardCommand board = boardService.selectBoard(boardCommand.getSeq());
		String oldFileName = "";
		
		//���� ���ϸ��� ����
		//���ε�Ǵ� ������ ���� ��� ���� ������ ���� ���ο� ���ϸ� ���� ���εǴ� ������ ������� ���� ���ϸ��� ����
		oldFileName = board.getFilename();
		
		if(!boardCommand.getUpload().isEmpty()){
			//���۵� ������ ���� ���
			boardCommand.setFilename(FileUtil.rename(boardCommand.getUpload().getOriginalFilename()));
		}else{
			//���۵� ������ ���� ���
			boardCommand.setFilename(oldFileName);
		}
		
		//�� ����
		boardService.update(boardCommand);
		status.setComplete();
		
		if(!boardCommand.getUpload().isEmpty()){
			//���۵� ������ ���� ���
			File file = new File(FileUtil.UPLOAD_PATH+"/"+boardCommand.getFilename());
			boardCommand.getUpload().transferTo(file);
			
			if(oldFileName!= null){
				//���� ���� ����
				FileUtil.removeFile(oldFileName);
			}
		}
		return "redirect:/board/list.do";
	}
}
