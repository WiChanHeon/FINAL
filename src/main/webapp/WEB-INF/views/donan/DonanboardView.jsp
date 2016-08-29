<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/board.reply.js"></script>
<div>
   <h2 class="align-center">${board.title}</h2>
   <ul>
      <li>번호 : ${board.seq}</li>
      <li>작성자 : ${board.id}</li>
      <li>조회수: ${board.hit}</li>
      <li>등록날짜: ${board.regdate}</li>
      <c:if test="${!empty board.filename}">
      <li>첨부파일 : <a href="file.do?filename=${board.filename}">${board.filename}</a></li>
      </c:if>
   </ul>
   <hr size="1" width="100%">
   <p>
      ${board.content}
   </p>
   <hr size="1" width="100%">
   <p class="align-right">
      <c:if test="${!empty userId && userId == board.id}">
      <input type="button" value='수정' onclick="location.href='update.do?seq=${board.seq}'">
      <input type="button" value='삭제' onclick="location.href='delete.do?seq=${board.seq}'">
      <input type="button" value='목록' onclick="location.href='list.do'">
      </c:if>
   </p>
   <span class="reply-title">댓글 달기</span>
   <form id="re_form">
      <input type="hidden" name="seq" value="${board.seq}" id="seq">
      <input type="hidden" name="id" value="${userId}" id="userId">
      <textarea rows="3" cols="50" name="re_content" id="re_content" maxlength="300" class="rep-content"
      <c:if test="${empty userId}">disable="disabled"</c:if>
      ><c:if test="${empty userId}">로그인 해야 작성할 수 있습니다.</c:if></textarea>
      <c:if test="${!empty userId}">
      <div id="re_first">
         <span class="letter-count">300/300</span>
      </div>
      <div id="re_second" class="align-right">
         <input type="submit" value="전송">
      </div>
      </c:if>
   </form>
   <!-- 목록 출력 -->
   <div id="output"></div>
   <div class="paging_putton" style="display:none;">
      <input type="button" value="다음글 보기">
   </div>
   <div id="loading" style="display:none;">
      <img src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif">
   </div>
   <!-- 수정폼 -->
   <div id="modify_div" style="display:none;">
      <form id="mre_form">
         <input type="hidden" name="re_no" id="mre_no">
         <input type="hidden" name="id" id="muserId">
         <textarea rows="3" cols="50" name="re_content"
          id="mre_content" class="rep-content"></textarea>
          <div id="mre_first"><span class="letter-count">300/300</span></div>
          <div id="mre_second" class="align-right">
             <input type="submit" value="수정">
             <input type="button" value="취소" class="re-reset">
          </div> 
          <hr size="1" noshade width="96%">
      </form>
   </div>
</div>