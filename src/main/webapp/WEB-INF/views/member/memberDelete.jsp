<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div>
	<h2>
		<spring:message code="member.delete.title" />
	</h2>
	<form:form action="delete.do" commandName="command">
		<form:errors element="div" class="error-color"/>
		<ul>
			<li>
				<label for="passwd">비밀번호</label>
				<form:password path="passwd"/>
				<form:errors path="passwd" class="error-color"/>
			</li>
			<li class="align-center">
				<input type="submit" value="전송">
				<input type="button" value="회원상세정보" onclick="location.href='detail.do'">
				<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
			</li>	
		</ul>
	</form:form>
</div>