<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/>
</sec:authorize>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div id="nav">
    <ul class="nav nav-pills nav-justified">
        <li>
            <a href="/mainPage.jsp">
                <img src="/images/logo.jpeg" alt="로고">
            </a>
        </li>

        <li class="nav-item">
            <a class="nav-link"  href="<c:url value='/'/>" id="home_link">회사소개</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/board/list'/>" id="board_link">게시물</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/member/list'/>" id="member_link">회원관리</a>
        </li>
        <c:choose>
            <c:when test="${empty principal}">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/users/loginForm/'/>" id="login_link">로그인</a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/users/logout/'/>" id="logout_link">${principal.member_name}</a>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
</div>
</body>
</html>
