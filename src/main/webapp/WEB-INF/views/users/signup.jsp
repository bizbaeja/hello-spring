<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>a
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href='<c:url value="/css/signup.css"/>'>
<title>Insert title here</title>
</head>
<body>
 <div id="wrap">
    <nav>
          <div id="nav">
		        <div class="header-nav">
			 	    <a href="mainPage.jsp"><img src="logo.jpeg" alt="로고"></a>
			    <h1><a href="mainPage.jsp">bizbaeja</a></h1>
					<a href="/users/about">소개</a>
					<a href="/users/signupForm">회원가입</a>
					<a href="/users/loginForm">로그인</a>
					<a href="user.do?action=list">회원정보</a>
					<a href=/boards/list>게시판</a>
				</div>  	
				<div class="header-nav">
				  <c:if test="${sessionScope.userid != null}">
				   <a href="#" id="logoutLink">로그아웃</a>
				   <a href="updateForm.jsp">회원정보수정</a>
	   			</c:if>
			    </div>
          회원가입에 성공하셨습니다. 
	
        </div>
    </nav>
    
        </div>

    
</body>
</html>