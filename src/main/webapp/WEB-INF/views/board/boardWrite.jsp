<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div>
	<h2>
		<spring:message code="board.write.title"/></h2>
	<form:form action="write.do" enctype="multipart/form-data" commandName="command" id="register_form">
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
			</li>
			<li class="align-center">
				<input type="submit" value="전송">
				<input type="button" value="목록" onclick="location.href='list.do'">
			</li>
		</ul>
	</form:form>
</div>