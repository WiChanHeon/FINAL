<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div>
	<h2>
		<spring:message code="member.update.title" />
	</h2>
	<form:form action="update.do" commandName="command" id="modify_form">
		<ul>
			<li><label for="name">이름</label> <form:input path="name" maxlength="5"/> <form:errors
					path="name" class="error-color" /></li>
			<li><label for="passwd">비밀번호</label> <form:password
					path="passwd" maxlength="10"/> <form:errors path="passwd" class="error-color" /></li>
			<li><label for="email">이메일</label> <form:input path="email" maxlength="20"/>
				<form:errors path="email" class="error-color" /></li>
			<li class="align-center"><input type="submit" value="전송">
				<input type="button" value="홈으로"
				onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
			</li>
		</ul>
	</form:form>
</div>