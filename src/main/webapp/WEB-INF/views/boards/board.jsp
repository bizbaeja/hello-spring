<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href='<c:url value="/css/signup.css"/>'>
<title>Insert title here</title>
</head>
<body>
 <div id="wrap">
     <div class="header-nav">
         <a href="user.do?action=about">소개</a> <a
         <a href="/users/about">소개</a>
         <a href="/users/signupForm">회원가입</a>
         <a href="user.do?action=list">회원정보</a>
         <a href=/boards/list>게시판</a>
     </div>  	<div class="header-nav">
     <c:if test="${sessionScope.userid != null}">
         <a href="#" id="logoutLink">로그아웃</a>
         <a href="updateForm.jsp">회원정보수정</a>
     </c:if>
 </div>

     <nav>



     <div id="contents">
        <div class="vertical-container">
           <div class="post-view">
                <h2>게시글 제목</h2>
                <p>작성자: ${boardVO.userid}</p>
                <p>작성일: ${boardVO.postdate}</p>
                <p>내용: 게시글 내용</p>
            </div>
            <form class="board-form" action="board.do" method="post">
                <div class="form-control">
                    <label for="title">제목:</label>
                    <input type="text" id="title" name="title" required>
                </div>
                <div class="form-control">
                    <label for="content">내용:</label>
                    <textarea id="content" name="content" rows="5" required></textarea>
                </div>
                <div class="form-control">
                    <button type="submit">게시글 작성</button>
                </div>
                
            </form>
            </div>
        </div>

    

    </div>
        <script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/update.js'/>"></script>
</body>
</html>