<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<h2>
		<spring:message code="board.update.title"/></h2>
	<form:form action="update.do" enctype="multipart/form-data" commandName="command" id="modify_form">
		<form:errors element="div"/>
		<ul>
			<li>
				<label for="title">제목</label>
				<form:input path="title"/>
				<form:errors path="title" class="error-color"/>
			</li>
			<li>
				<label for="content">내용</label>
				<form:textarea path="content"/>
				<form:errors path="content" class="error-color"/>
			</li>
			<li>
				<label for="upload">파일업로드</label>
				<input type="file" name="upload" id="upload"/>
				<c:if test="${!empty command.filename}">
				<br>
				<span>(${command.filename})파일이 등록되어 있습니다. 다시 업로드하면 기존 파일이 삭제됩니다.</span>
				</c:if>
			</li>
			<li class="align-center">
				<input type="submit" value="전송">
				<input type="button" value="목록" onclick="location.href='list.do'">
			</li>
		</ul>
	</form:form>
</div>