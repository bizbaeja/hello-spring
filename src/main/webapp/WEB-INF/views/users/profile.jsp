<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="/WEB-INF/views/include/meta.jsp" %>
    <%@ include file="/WEB-INF/views/include/css.jsp" %>
    <%@ include file="/WEB-INF/views/include/js.jsp" %>
    <title>User Profile</title>
</head>
<body>
<h1>User Profile</h1>
<h2>User Information</h2>
<table border="1">
    <tr>
        <th>User ID</th>
        <th>Name</th>
        <th>Address</th>
        <th>Phone Number</th>
        <th>Gender</th>
    </tr>
    <tr>
        <td>${user.member_id}</td>
        <td>${user.member_name}</td>
        <td>${user.member_address}</td>
        <td>${user.member_phone_number}</td>
        <td>${user.member_gender}</td>
    </tr>
</table>

<h2>Your Posts</h2>
<c:if test="${not empty posts}">
    <ul>
        <c:forEach items="${posts}" var="post">
            <li>${post.title} - ${post.content}</li>
        </c:forEach>
    </ul>
</c:if>
<c:if test="${empty posts}">
    <p>No posts found.</p>
</c:if>
</body>
</html>
