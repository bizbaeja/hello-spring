<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Board Post</title>
    <link rel="stylesheet" type="text/css" href='<c:url value="/css/board.css"/>'>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.ckeditor.com/ckeditor5/12.4.0/classic/ckeditor.js"></script>
    <script src="https://ckeditor.com/apps/ckfinder/3.5.0/ckfinder.js"></script>
</head>
<body>
<div id="wrap">

    <%@ include file="/WEB-INF/views/include/header.jsp" %>
    <div id="content">
        <form action="${pageContext.request.contextPath}/boards/upload" method="post" enctype="multipart/form-data">
            Title: <input type="text" name="title"><br>
            Content: <textarea name="content" id="editor"></textarea><br>
            <input type="file" name="file"><br>
            <input type="submit" value="Upload">
        </form>
    </div>
</div>
s
<script>
    ClassicEditor
        .create(document.querySelector('#editor'), {
            ckfinder: {
                uploadUrl: '<c:url value="/boards/upload"/>'
            }
        })
        .catch(error => {
            console.error('CKEditor initialization error:', error);
        });
</script>
</body>
</html>

