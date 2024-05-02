<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Member List</title>
</head>
<body>

<h1>Member List</h1>

<c:if test="${!empty successMessage}">
    <div id="message" class="success">${successMessage}</div>
</c:if>
<c:if test="${!empty errorMessage}">
    <div id="message" class="error">${errorMessage}</div>
</c:if>

<form:form action="${pageContext.request.contextPath}/users/admin/member/lockunlock" method="POST">
    <table>
        <thead>
        <tr>
            <th>Check</th>
            <th>ID</th>
            <th>Name</th>
            <th>Gender</th>
            <th>Phone Number</th>
            <th>Roles</th>
            <th>Account Locked</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${members}" var="member">
            <tr>
                <td><input type="checkbox" name="memberIds" value="${member.member_id}"/></td>
                <td>${member.member_id}</td>
                <td>${member.member_name}</td>
                <td>${member.member_gender}</td>
                <td>${member.member_phone_number}</td>
                <td>${member.member_roles}</td>
                <td>${member.member_account_locked}</td>
                <td style="display: flex">
                    <!-- Lock/Unlock 버튼 -->
                    <button type="submit" name="action" value="toggleLock" formaction="${pageContext.request.contextPath}/users/admin/member/lockunlock/${member.member_id}">
                            ${member.member_account_locked == 'Y' ? 'Unlock' : 'Lock'}
                    </button>
                    <!-- Delete button sends directly without checkbox -->
                    <form:form method="post" action="${pageContext.request.contextPath}/users/admin/member/delete">
                        <input type="hidden" name="memberIds" value="${member.member_id}" />
                        <button type="submit" name="action" value="delete">Delete</button>
                    </form:form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- 선택한 회원들을 잠금/해제 -->
    <input type="submit" value="Lock/Unlock Selected" />

<!-- 선택한 회원들을 삭제 -->
<button type="submit" formaction="${pageContext.request.contextPath}/users/admin/member/delete">Delete Selected</button>
</form:form>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        var message = document.getElementById('message');
        if (message) {
            alert(message.className + ': ' + message.textContent);
        }
    });
</script>

</body>
</html>