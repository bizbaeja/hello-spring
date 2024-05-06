<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Board Detail</title>
</head>
<body onload="updateViewCount(${board.board_id})">
<h1>Board Details</h1>
<div>
    <h2>Title: ${board.title}</h2>
    <p><strong>ID:</strong> ${board.board_id}</p>
    <p><strong>Member ID:</strong> ${board.member_id}</p>
    <p><strong>Content:</strong> ${board.content}</p>
    <p><strong>Created At:</strong> ${board.created_at}</p>
    <p><strong>View Count:</strong> ${board.view_count}</p>
</div>

<c:if test="${not empty board.boardFiles}">
    <h3>Attached Files and Images:</h3>
    <ul>
        <c:forEach items="${board.boardFiles}" var="file">
            <li>
                    ${file.originalFilename} (Size: ${file.size} bytes)

                        <!-- Display the image -->
                        <img src="/images/${file.realFilename}" alt="${file.originalFilename}" style="width: 100px; height: auto;">


            </li>
        </c:forEach>
    </ul>
</c:if>

<a href="${pageContext.request.contextPath}/boards/list">Back to List</a>
<script>
    function updateViewCount(boardId) {
        fetch(`/boards/${boardId}/viewcount`, { method: 'GET' })
            .then(response => response.json())
            .then(data => {
                if (data && data.viewCount) {
                    document.getElementById('viewCount').textContent = data.viewCount;
                }
            })
            .catch(error => console.error('Error fetching view count:', error));
    }
</script>
</body>
</html>
