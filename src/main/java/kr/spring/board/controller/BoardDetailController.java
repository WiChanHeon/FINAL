package kr.spring.board.controller;

import java.io.File;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.domain.BoardCommand;
import kr.spring.board.service.BoardService;
import kr.spring.util.FileUtil;
import kr.spring.util.StringUtil;

@Controller
public class BoardDetailController {
   private Logger log = Logger.getLogger(this.getClass());
   
   @Resource
   private BoardService boardService;
   
   @RequestMapping("/board/detail.do")
   public ModelAndView process(@RequestParam("seq") int seq){
      
      if(log.isDebugEnabled()){
         log.debug("seq : " + seq);
      }
      
      //해당 글의 조회수 증가
      boardService.updatehit(seq);
      BoardCommand board = boardService.selectBoard(seq);
      
      //타이틀 태그 불허
      board.setTitle(StringUtil.useNoHtml(board.getTitle()));
      
      //줄바꿈처리
      board.setContent(StringUtil.useBrNoHtml(board.getContent()));
      return new ModelAndView("boardView","board",board);
   }

   //파일 다운로드
   @RequestMapping("/board/file.do")
   public ModelAndView download(@RequestParam("filename") String filename)throws Exception{
	   
	   File downloadFile = new File(FileUtil.UPLOAD_PATH+"/"+filename);
	   
	   return new ModelAndView("downloadView","downloadFile",downloadFile);
   }
   
}