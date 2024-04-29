<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>`
    <meta charset="UTF-8">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <link rel="stylesheet" type="text/css" href='<c:url value="/css/board.css"/>'>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <%-- ckeditor 관련 자바 스크립트  --%>
    <script src="https://cdn.ckeditor.com/ckeditor5/12.4.0/classic/ckeditor.js"></script>
    <script src="https://ckeditor.com/apps/ckfinder/3.5.0/ckfinder.js"></script>
    <%@ include file="/WEB-INF/views/include/meta.jsp" %>
    <%@ include file="/WEB-INF/views/include/css.jsp" %>
    <%@ include file="/WEB-INF/views/include/js.jsp" %>
    <title>Board Post</title>
</head>
<body>
    <div id="wrap">
        <%@ include file="/WEB-INF/views/include/header.jsp" %>
            <div class="header-nav">
                <c:if test="${sessionScope.userid != null}">
                    <a href="#" id="logoutLink">로그아웃</a>
                    <a href="updateForm.jsp">회원정보수정</a>
                </c:if>
            </div>
        </div>

        <div id="content">
            <div class="vertical-container">
                <c:choose>
                    <c:when test="${not empty board}">
                        <div class="post-view">
                            <h2>게시글 제목: ${board.title}</h2>
                            <p>작성자: ${board.userid}</p>
                            <p>작성일: ${board.postdate}</p>
                            <p>내용: ${board.content}</p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <h1>
                            게시물 등록양식
                        </h1>
                        <%-- 로그인 사용자 정보 출력 --%>
                        <h3>로그인 : <sec:authentication property="principal.member_name" /></h3>

                        <form id="rForm" action="insert" method="post" enctype="multipart/form-data">
                            <!-- 게시물 토큰을 설정한다 -->		<sec:csrfInput/>
                            <input type="hidden" id="board_token" name="board_token" value="${board_token}"><br/>
                            <input class="btitle" id="btitle" name="btitle" required="required" placeholder="게시물 제목을 입력해주세요"><br/>
                            <textarea id="bcontent" name="bcontent" required="required" placeholder="게시물 내용을 입력해주세요">
                           </textarea>
                            <div id="div_file">
                                <input  type='file' name='file' />
                            </div>
                            <br/>
                            <div>
                                <input type="submit"  onclick="myFileFetch('insert', 'rForm', handlerFunction)">
                                <a href="javascript:history(-1)">취소</a>
                            </div>

                        </form>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/boardInsert.js'/>"></script>
</body>
</html>
