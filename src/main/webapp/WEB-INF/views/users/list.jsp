<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href='<c:url value="/css/list.css"/>'>

    <%@ include file="/WEB-INF/views/include/meta.jsp" %>
    <%@ include file="/WEB-INF/views/include/css.jsp" %>
    <%@ include file="/WEB-INF/views/include/js.jsp" %>
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
</head>
<body>
 <div id="wrap">

     <%@ include file="/WEB-INF/views/include/header.jsp" %>
		로그인되었습니다.  
       
<h1>회원목록</h1>
	  
    <form id="searchForm" action="user.do?action=list" method="get" >
    	<label>이름 : </label>
    	<input type="text" id="searchKey" name="searchKey" value="${param.searchKey}">
    	<input type="submit" value="검색">
    </form>
    
    <form id="list" action="user.do" method="post">
    	<input type="hidden" id="action" name="action" value="read">
    	<input type="hidden" id="userid" name="userid" >
    </form>
    
    <table border="1">
        <tr>
            <th>ID</th>
            <th>이름</th>
            <th>이메일</th>
            <th>생일</th>
            <th>성별</th>
            <th>등록일</th>
        </tr>
        <c:forEach var="user" items="${usersList}">
        <tr>
            <td onclick="jsView('${user.userid}')"  style="cursor:pointer;">${user.userid}</td>
            <td><a href="user.do?action=read&userid=${user.userid}">${user.name}</a></td>
            <td>${user.email}</td>
            <td>${user.birth}</td>
            <td>${user.gender}</td>
            <td>${user.register}</td>
        </tr>
        </c:forEach>
    </table>
    
 </div >
</body>
</html>