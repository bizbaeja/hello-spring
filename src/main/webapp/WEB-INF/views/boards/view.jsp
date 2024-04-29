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
    <title>게시물 상세보기</title>
    <style>
        label { display: inline-block; width: 200px; }
        input, a { margin-bottom: 10px; }
    </style>
</head>
<body>
<h1>게시물 상세보기</h1>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<label>게시물 번호: ${board.bno}</label><br/>
<label>제목: ${board.btitle}</label><br/>
<label>내용: ${board.bcontent}</label><br/>
<label>ViewCount: ${board.BViewCount}</label><br/>
<label>작성자: ${board.member_id}</label><br/>
<label>작성일: ${board.bdate}</label><br/>

<div>


    <form id="viewForm" method="post" action="view">
        <input type="hidden" name="bno" value="${board.bno}">
        <input type="button" value="삭제" onclick="jsDelete()">
        <input type="button" value="수정" onclick="jsUpdateForm()">
    </form>
</div>

<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/delete.js'/>"></script>
<script>
    menuActive("board_link");

    function jsDelete() {
        if (confirm("정말로 삭제하시겠습니까?")) {
            myFetch("delete", "viewForm", json => {
                if(json.status == 0) {
                    //성공
                    alert("게시물 정보를 삭제 하였습니다");
                    location = "list";
                } else {
                    alert(json.statusMessage);
                }
            });
        }
    }

    function jsUpdateForm() {
        if (confirm("정말로 수정하시겠습니까?")) {
            //서버의 URL을 설정한다
            viewForm.action = "updateForm";

            //서버의 URL로 전송한다
            viewForm.submit();
        }
    }
</script>
</body>
</html>
