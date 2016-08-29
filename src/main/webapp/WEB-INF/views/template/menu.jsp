<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="menu">
	<c:if test="${empty userId}">
		<li><a href="${pageContext.request.contextPath}/member/write.do">회원가입</a></li>
		<li><a href="${pageContext.request.contextPath}/member/login.do">로그인</a></li>
	</c:if>
	<c:if test="${!empty userId}">
		<li><a href="${pageContext.request.contextPath}/member/detail.do">회원정보</a></li>
		<li>${userId}님 로그인중</li>
		<li><a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a></li>
	</c:if>
</ul>