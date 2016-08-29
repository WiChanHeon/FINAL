<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/confirmId.js"></script>
<div>
	<h2>
		<spring:message code="member.write.title" />
	</h2>
	<form:form action="write.do" commandName="command" id="register_form">
		<ul>
			<li><label for="id">아이디</label> <form:input path="id" maxlength="12"/> 
			<input type="button" id="confirmId" value="ID중복체크">
			<span id="id_signed" class="error-color"></span>
			<img src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif"
				width="16" height="16" id="loading" style="display:none;">
			<form:errors
					path="id" class="error-color" /></li>
			<li><label for="name">이름</label> <form:input path="name" maxlength="5"/> <form:errors
					path="name" class="error-color" /></li>
			<li><label for="passwd">비밀번호</label> <form:password path="passwd" maxlength="10"/> 
			<form:errors path="passwd" class="error-color" /></li>
			<li><label for="email">이메일</label> <form:input path="email" maxlength="20"/>
				<form:errors path="email" class="error-color" /></li>
			<li class="align-center"><input type="submit" value="전송">
				<input type="button" value="홈으로"
				onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
			</li>
		</ul>
	</form:form>
</div>