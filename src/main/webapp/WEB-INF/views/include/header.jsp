<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

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

	<!-- 로그인/로그아웃 링크 조정 -->
	<sec:authorize access="isAuthenticated()">
		<li class="nav-item">
			<sec:authorize access="hasAuthority('ROLE_ADMIN')">
				<!-- ADMIN 역할을 가진 사용자에게만 보이는 추가 내용 -->
				<div>
					<a href="/users/admin/member/list">관리자메뉴</a>
				</div>
				<!-- 로그아웃 링크 표시 -->
				<a class="nav-link" href="<c:url value='/users/logout/'/>" id="logout_link">로그아웃</a>
			</sec:authorize>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="<c:url value='/users/profile/'/>" id="profile_link">나의정보</a>
		</li>
	</sec:authorize>
	<sec:authorize access="isAnonymous()">
		<li class="nav-item">
			<!-- 로그인 링크 표시 -->
			<a class="nav-link" href="<c:url value='/users/loginForm/'/>" id="login_link">로그인</a>
		</li>
	</sec:authorize>
</ul>
