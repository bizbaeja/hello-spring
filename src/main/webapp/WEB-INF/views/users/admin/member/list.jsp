<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Member List</title>
    <!-- 여기에 필요한 CSS와 JavaScript를 포함시킵니다. -->
</head>
<body>

<h1>Member List</h1>

<c:if test="${!empty message}">
    <div id="message" class="${status}">${message}</div>
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
                <td>
                    <!-- Lock/Unlock 버튼 -->
                    <button type="submit" name="action" value="toggleLock" formaction="${pageContext.request.contextPath}/users/admin/member/lockunlock/${member.member_id}">
                            ${member.member_account_locked == 'Y' ? 'Unlock' : 'Lock'}
                    </button>
                    <!-- Delete 버튼 -->
                    <button type="submit" name="action" value="delete" formaction="${pageContext.request.contextPath}/users/admin/member/delete/${member.member_id}">
                        Delete
                    </button>
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
    window.addEventListener('DOMContentLoaded', (event) => {
        // 스프링에서 설정한 Flash Attribute를 확인
        let message = '${message}';
        let status = '${status}';

        if (message) {
            if (status === 'success') {
                // 성공 메시지를 보여줍니다.
                alert('Success: ' + message);
            } else if (status === 'error') {
                // 오류 메시지를 보여줍니다.
                alert('Error: ' + message);
            }
        }
    });
</script>

</body>
</html>
