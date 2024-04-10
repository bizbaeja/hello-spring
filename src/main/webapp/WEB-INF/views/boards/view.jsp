<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>게시물 상세보기</title>
    <style>
        label { display: inline-block; width: 200px; }
        input, a { margin-bottom: 10px; }
    </style>
</head>
<body>
<h1>게시물 상세보기</h1>
<nav>
    <a href="user.do?action=about">소개</a>
    <a href="/users/about">소개</a>
    <a href="/users/signupForm">회원가입</a>
    <a href="user.do?action=list">회원정보</a>
    <a href="/boards/list">게시판</a>
</nav>
<label>게시물 번호: ${board.boardid}</label><br/>
<label>제목: ${board.title}</label><br/>
<label>내용: ${board.content}</label><br/>
<label>ViewCount: ${board.viewCount}</label><br/>
<label>작성자: ${board.userid}</label><br/>
<label>작성일: ${board.postdate}</label><br/>

<div>
    <a href="/boards/updateForm?boardid=${board.boardid}">수정</a>
    <form action="/boards/delete" method="post" style="display: inline;">
        <input type="hidden" name="boardid" value="${board.boardid}">
        <input type="submit" value="삭제" onclick="return confirm('정말로 삭제하시겠습니까?')">
    </form>
    <a href="/boards/list">목록</a>
</div>

<script type="text/javascript" src="<c:url value='/resources/js/common.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/delete.js'/>"></script>
</body>
</html>
