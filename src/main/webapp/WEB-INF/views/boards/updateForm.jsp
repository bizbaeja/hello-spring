<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%@ include file="/WEB-INF/views/include/meta.jsp" %>
    <%@ include file="/WEB-INF/views/include/css.jsp" %>
    <%@ include file="/WEB-INF/views/include/js.jsp" %>
    <title>수정화면</title>
</head>
<body>

<h1>
    게시물 수정 수정양식
</h1>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<form id="rForm" action="" method="post">
    <label>게시물 번호: </label> <input type="text" id="boardid" name="boardid" value="${board.boardid}" readonly="readonly"> <br/>
    <label>제목 : </label><input type="text" id="title" name="title" value="${board.title}"><br/>
    <label>내용: </label> <input type="text" id="content" name="content" value="${board.content}"><br/>
    <div>
        <input type="submit" value="수정">
        <a href="javascript:history(-1)">취소</a>
    </div>

</form>

<script type="text/javascript">

    const rForm = document.getElementById("rForm");
    rForm.addEventListener("submit", e => {
        //서버에 form data를 전송하지 않는다
        e.preventDefault();

        myFetch("update", "rForm", json => {
            if(json.status == 0) {
                //성공
                alert("게시물 정보 수정을 성공 하였습니다");
                location = "view?boardid=" + boardid.value;
            } else {
                alert(json.statusMessage);
            }
        });
    });

</script>
<script type="text/javascript" src="<c:url value='js/common.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/boardUpdate.js'/>"></script>

</body>
</html>

