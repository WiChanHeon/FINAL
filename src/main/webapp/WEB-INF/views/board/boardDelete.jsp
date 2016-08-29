<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div>
	<h2><spring:message code="board.delete.title"/></h2>
	<form:form action="delete.do" commandName="command">
		<form:errors element="div" class="error-color"/>
		비밀번호 : <form:password path="pwd"/>
		<form:errors path="pwd" class="error-color"/><br>
		<input type="submit" value="전송">
		<input type="button" value="목록" onclick="location.href='list.do'">
	</form:form>
</div>