<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<h2><spring:message code="board.list.title"/></h2>
	<form action="list.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield">
					<option value="title">수정</option>
					<option value="writer">수정2</option>
					<option value="content">수정3</option>
					<option value="all">수정4</option>
				</select>
			</li>
			<li>
				<input type="text" size="16" name="keyword">
			</li>
			<li>
				<input type="submit" value="찾기">
				<input type="button" value='목록' onclick="location.href='list.do'">
			</li>
		</ul>
	</form>
	<table>
		<tr>
			<td align="right">
				<input type="button" value="글쓰기" onclick="location.href='write.do'">
			</td>
		</tr>
	</table>
	<c:if test="${count == 0}">
	<div align="center">등록된 게시물이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<table border="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>ID</th>
			<th>날짜</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="article" items="${list}">
		<tr>
			<td>${article.seq}</td>
			<td><a href="detail.do?seq=${article.seq}">${article.title}</a></td>
			<td>${article.id}</td>
			<td>${article.regdate}</td>
			<td>${article.hit}</td>
		</tr>
		</c:forEach>
	</table>
	<table>
		<tr>
			<td align="center">${pagingHtml}</td>
		</tr>
	</table>
	</c:if>
</div>