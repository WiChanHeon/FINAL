<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>      
<div>
	<h2><spring:message code="member.detail.title" arguments="${member.id}"/></h2>
	<ul>
		<li>아이디 : ${member.id}</li>
		<li>이름 : ${member.name}</li>
		<li>이메일 : ${member.email}</li>
		<li>가입날짜 : ${member.reg_date}</li>
	</ul>
	<hr size="1" width="100%">
	<p class="align-right">
		<input type="button" value="수정" onclick="location.href='update.do'">
		<input type="button" value="회원탈퇴" onclick="location.href='delete.do'">
	</p>
</div>
