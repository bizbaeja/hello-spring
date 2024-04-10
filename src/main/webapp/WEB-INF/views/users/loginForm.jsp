<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href='<c:url value="/resources/css/login.css"/>'>
<title>회원가입</title>
</head>
<body>
	<div class="wrap">
		<!-- 생략된 네비게이션 및 기타 코드 -->
		<div id="nav">
			<div class="header-nav">
				<a href="mainPage.jsp"><img src="logo.jpeg" alt="로고"></a>
				<h1>
					<a href="mainPage.jsp">bizbaeja</a>
				</h1>
				<a href="user.do?action=about">소개</a> <a
				<a href="/users/about">소개</a>
				<a href="/users/signupForm">회원가입</a>
				<a href="user.do?action=list">회원정보</a>
				<a href=/boards/list>게시판</a>
			</div>
			<div class="header-nav">
				<c:if test="${sessionScope.userid != null}">
					<a href="#" id="logoutLink">로그아웃</a>
					<a href="updateForm.jsp">회원정보수정</a>
				</c:if>
			</div>
		</div>

		<qdiv class="user-detail">
			<h2>로그인</h2>
			<form id="loginForm"  method="post">
<%--				<input type="hidden" name="action" value="login"> --%>
				<label>아이디: </label> 
					<input type="text" id="userid" name="userid" required="required">
					<br /> 
					<label>비밀번호 : </label> 
					<input type="password" id="password" name="password" required="required">
					<br />
				<!-- <label for="autologin">자동로그인</label> <input type="checkbox" id="autologin" name="autologin" value="Y">     -->
				<div>
					<input type="submit" value="로그인">
				</div>

			</form>
		</qdiv>
	</div>
	<script type="text/javascript" src="<c:url value='/resources/js/common.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/login.js'/>"></script>
</body>
</html>
