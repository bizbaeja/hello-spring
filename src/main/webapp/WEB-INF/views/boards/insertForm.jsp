<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>등록화면</title>
    <%@ include file="/WEB-INF/views/include/meta.jsp" %>
    <%@ include file="/WEB-INF/views/include/css.jsp" %>
    <%@ include file="/WEB-INF/views/include/js.jsp" %>
    <script src="https://cdn.ckeditor.com/ckeditor5/12.4.0/classic/ckeditor.js"></script>
    <script src="https://ckeditor.com/apps/ckfinder/3.5.0/ckfinder.js"></script>
    <style>
        label {
            display: inline-block;
            width: 120px;
        }
        input {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

<%@ include file="/WEB-INF/views/include/header.jsp" %>
<form id="rForm" method="post" enctype="multipart/form-data">
    <label>제목 : </label><input type="text" id="title" name="title" required="required"><br/>
    <textarea id="content" name="content"></textarea>
    <div id="div_file">
        <input  type='file' name='file' />
    </div>
    <br/>
    <div>
        <input type="submit" value="등록">
        <a href="javascript:history(-1)">취소</a>
    </div>
    <input type="hidden" id="writer" name="writer" value="${loginUser}"><br/>
</form>

<%--<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>--%>

<script type="text/javascript">
    const csrfParameter = document.querySelector("meta[name='_csrf_parameter']").content;
    const csrfToken = document.querySelector("meta[name='_csrf']").content;
    const board_image_url = "<c:url value='/boards/insert'/>" + csrfParameter + "=" + csrfToken;
    ClassicEditor
        .create(document.querySelector('#content'), {
            ckfinder: {
                uploadUrl: board_image_url
            }
        })
        .then( editor => {
            console.log(editor);
        })
        .catch( error => {
            console.error(error);
        });

    const rForm = document.getElementById("rForm");
    rForm.addEventListener("submit", e => {
        e.preventDefault();

        myFileFetch("insert", "rForm", json => {
            if(json.status == 0) {
                //성공
                alert("게시물을 등록 하였습니다");
                location = "/boards/list";
            } else {
                alert(json.statusMessage);
            }
        });
    });

</script>

</body>
</html>
