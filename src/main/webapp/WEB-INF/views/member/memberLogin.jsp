<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>     
<div>
	<h2><spring:message code="member.login.title"/></h2>
	<form:form action="login.do" commandName="command" id="login_form">
		<form:errors element="div" class="error-color"/>
		<ul>
			<li>
				<label for="id">아이디</label>
				<form:input path="id"/>
				<form:errors path="id" class="error-color"/>
			</li>
			<li>
				<label for="passwd">비밀번호</label>
				<form:password path="passwd"/>
				<form:errors path="passwd" class="error-color"/>
			</li>
			<li class="align-center">
				<input type="submit" value="전송">
				<input type="button" value="홈으로"
					onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
			</li>
		</ul>
	</form:form>
</div>