<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal"/>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
	<a href="/profile">My Profile</a>
</sec:authorize>
<sec:authorize access="hasRole('ADMIN')">
	<a href="/users/admin/member/list">Admin Dashboard</a>
</sec:authorize>

<ul class="nav nav-pills nav-justified">
	<li class="nav-item">
		<a class="nav-link" href="<c:url value='/'/>" id="home_link">회사소개</a>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="<c:url value='/boards/list'/>" id="board_link">게시물</a>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="<c:url value='/member/list'/>" id="member_link">회원관리</a>
	</li>

	<sec:authorize access="!isAuthenticated()">
		<li class="nav-item">
			<a class="nav-link" href="<c:url value='/users/loginForm'/>" id="login_link">로그인</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="<c:url value='/users/signupForm'/>" id="signup_link">회원가입</a>
		</li>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<li class="nav-item">
			<a class="nav-link" href="<c:url value='/users/logout/'/>" id="logout_link">로그아웃 (${principal.username})</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="<c:url value='/users/list/'/>" id="profile_link">나의정보</a>
		</li>
	</sec:authorize>
</ul>
