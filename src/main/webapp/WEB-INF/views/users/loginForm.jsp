<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href='<c:url value="/css/login.css"/>'>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

	<link rel="stylesheet" type="text/css" href="<c:url value='/css/login.css'/>">
	<%@ include file="/WEB-INF/views/include/meta.jsp" %>
	<%@ include file="/WEB-INF/views/include/css.jsp" %>
<title>회원가입</title>
</head>
<body>
	<div class="wrap">

		<%@ include file="/WEB-INF/views/include/header.jsp" %>
		<div class="user-detail">
			<h2>로그인</h2>
			<form id="loginForm"  method="post" action="/users/login">
				<sec:csrfInput/>

				<label>아이디: </label>
					<input type="text" id="member_id" name="member_id" required="required">
					<br />
					<label>비밀번호 : </label>
					<input type="password" id="member_pwd" name="member_pwd" required="required">
					<br />
				<!-- <label for="autologin">자동로그인</label> <input type="checkbox" id="autologin" name="autologin" value="Y">     -->
				<div>
					<input type="submit" value="로그인">
				</div>

			</form>
		</div>
	</div>
	<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
</body>
</html>
